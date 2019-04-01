package com.sea.upms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.common.enums.ExceptionEnum;
import com.sea.common.exception.SweetException;
import com.sea.common.vo.PageResult;
import com.sea.upms.dto.UserDTO;
import com.sea.upms.mapper.UserMapper;
import com.sea.upms.pojo.Role;
import com.sea.upms.pojo.User;
import com.sea.upms.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryUser(){
        return  userMapper.selectAll();
    }


    public PageResult<UserVo> queryUserByPage(Integer page, Integer rows,
                                              String sortBy, Boolean desc, String key) {
      //分页允许最多查100条
        PageHelper.startPage(page,Math.min(rows,100));//开启分页
        //过滤查询条件
        Example example = new Example(User.class);
        if(StringUtils.isNotBlank(key)){
            log.info("查询条件为： " + key.toString());
            example.createCriteria().orLike("loginName","%"+ key +"%")
                    .orLike("realName","%" + key +"%")
            .orLike("phone" ,"%"+key + "%");
        }
        //排序条件
        if(StringUtils.isNotBlank(sortBy)){
            log.info(sortBy+" "+(desc? " DESC" : " ASC") + " 排序");
            example.setOrderByClause(sortBy+(desc ? " DESC" : " ASC"));
        }
        List<User> userList = userMapper.selectByExample(example);
        //获取总条数
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //Java8的特性,stream() map()/filter()等箭头函数
        List<UserVo> userVoList = userList.stream().map(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            //查询拥有角色
             // userVo.setRoles(userMapper.findRolesByUserId(user.getId()));
            userVo.setRoles(userMapper.findRolesByUser(user.getId()));
            //TODO 查询所属部门
            return userVo;
        }).collect(Collectors.toList());

        return new PageResult<>(pageInfo.getTotal(),userVoList);
    }

//添加用户
    @Transactional
    public Boolean addUser(UserDTO userDTO) {
        User user = new User();
       BeanUtils.copyProperties(userDTO,user);
        user.setCreateTime(LocalDateTime.now());
        int result= userMapper.insertSelective(user);
        log.info("insert user seccss ,result:{},  user:{} ", result  , user);
        result=saveUserRole(user.getId(),userDTO.getRoles());
        if (result==0) return false;
        else return true;
    }

    //物理删除
    @Transactional
    public Boolean deleteUser(Long id) {
        //0.先删除关联的角色关系
        userMapper.deleteUserRoles(id);
        //1.再删除用户本身
        int result= userMapper.deleteByPrimaryKey(id);
       log.info("delete User id: {}, and result:{}",id,result);
       if (result==0)
           return false;
        return true;
    }


    /**
     * 更新用户 包括更新用户的角色
     * @param userDTO
     * @return
     */
    @Transactional
    public Boolean updateUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setUpdateTime(LocalDateTime.now());
        int  result = userMapper.updateByPrimaryKeySelective(user);
        log.info("update User seccuss, result: {},  user: {}) ", result,user );

        //比较两个角色ids，删除旧角色ids有而新角色ids没有的roleId，添加新的有而旧的没有的roleid
        List<Long> nRoleIds = userDTO.getRoles();
        log.info("new Role ids: {} ",nRoleIds);
        List<Long> oRoleIds = userMapper.finRoleIds(userDTO.getId());
        log.info("old role ids, {}",oRoleIds);
        //0.先过滤 TODO
        List<Long> addRoleIds = new ArrayList<>();
        if(null != nRoleIds)
            addRoleIds =  nRoleIds.stream().filter(nid->!oRoleIds.contains(nid)).collect(Collectors.toList());
        log.info("添加的角色关系： {}",addRoleIds );
        List<Long> delRoleIds = oRoleIds.stream().filter(oId->!nRoleIds.contains(oId)).collect(Collectors.toList());
       log.info("删除的角色关系： {}",delRoleIds);
        //1.删除旧角色 TODO
        if(null != delRoleIds || 0 < delRoleIds.size() )
            deleteUserRoles(userDTO.getId(),delRoleIds);
        // 2.添加新角色
        if(null != addRoleIds || 0 < addRoleIds.size())
            saveUserRole(userDTO.getId(),addRoleIds);
        if (result==0)return false;
        return true;
    }

    //判断用户登录名是否存在
    public User exist(String loginName){
       Example example = new Example(User.class);
       log.info("参数为："+ loginName);
       example.createCriteria()
               .orEqualTo("loginName",loginName);
       User user1 = userMapper.selectOneByExample(example);
       log.info("查询到的用户为："+user1);
       //ToDo 需要完善
        return null;
    }
//添加用户并分配角色
    @Transactional
    public void saveUser(User user, List<Long> roleIds) {
      int resultCount = userMapper.insert(user);
      if(resultCount==0){
          throw new SweetException(ExceptionEnum.USER_CREATE_FAILED);
      }
      for(Long rid : roleIds){
          resultCount = userMapper.saveUserRole(user.getId(),rid);
          if(resultCount==0){
              throw new SweetException(ExceptionEnum.USER_CREATE_FAILED);
          }
      }
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    //@Transactional
    public int saveUserRole(Long userId, List<Long> roleIds) {
        roleIds.forEach(rid->{
          int result=  userMapper.saveUserRole(userId,rid);
          if(result==0)return ;
        });
        log.info("insert User and Role relationships seccuss, userid:"+userId+" roleIds: "+roleIds);
        return 1;
    }

//    //删除用户关联对应的角色
    public void deleteUserRole(Long userid, Long roleid) {
        userMapper.deleteUserRole(userid,roleid);
    }

    /**
     * 删除用户角色关联的关系
     * @param userId
     * @param roleIds
     */
    public void deleteUserRoles(Long userId,List<Long> roleIds){
        roleIds.forEach(rId->userMapper.deleteUserRole(userId,rId));
        log.info("delete User and Roles relationships  seccuss, userId:{},roleIds: {}",userId,roleIds);
    }

    public User findUser(Long userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    public User findUserByUser(String userName,String password) {
        log.info("user-servesi-findUserByUser");
        User u = new User();
        u.setLoginName(userName);
        u.setPassword(password);
        log.info(u.toString());
        return u;
        //return userMapper.selectOneByExample(u);
    }


    public UserVo findUserAllInfo(Long userid) {
        UserVo userVo = new UserVo();
        User user = userMapper.selectByPrimaryKey(userid);
        List<Role> roles = userMapper.findRolesByUser(userid);
        BeanUtils.copyProperties(user,userVo);
        userVo.setRoles(roles);
        log.info("select cesscuss,userId:{}, userVo:{}",userid,userVo);
        return userVo;
    }
}

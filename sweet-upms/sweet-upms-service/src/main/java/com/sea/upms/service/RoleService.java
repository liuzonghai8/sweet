package com.sea.upms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.common.vo.PageResult;
import com.sea.upms.mapper.RoleMapper;
import com.sea.upms.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

//    public List<User> queryUser(){
//        return  roleMapper.selectAll();
//    }

    /**
     * 分页查询角色
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    public PageResult<Role> queryRoleByPage(Integer page, Integer rows,
                                            String sortBy, Boolean desc, String key) {
        log.info("QueryParams, page: {}, rows: {},sortBy:{}, sort:{},searchKey:{}",page,rows,sortBy,desc,key);
        PageHelper.startPage(page,rows);//开启分页
        Example example = new Example(Role.class);
        //过滤查询条件
        if(StringUtils.isNotBlank(key)){
            example.createCriteria().orLike("name","%"+ key +"%")
                    .orLike("code","%" + key +"%")
            .orLike("description" ,"%"+key + "%")
            ;
        }
        //排序条件
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+(desc ? " DESC" : " ASC"));
//            String sortByClause = sortBy + (desc ? " DESC" : " ASC");
//            log.info("sortByClause "+ sortByClause);
//            example.setOrderByClause(sortByClause);
        }
        List<Role> roleList = roleMapper.selectByExample(example);
        //总条数
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        log.info("queryRoleBypage seccuss! total:{}, Roles:{}",pageInfo.getTotal(),roleList);
        return new PageResult<>(pageInfo.getTotal(),roleList);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    public Boolean addRole(Role role) {
        //0. 先检查是否存在
        if(check(role.getName()))
            return false;
         role.setCreateTime(LocalDateTime.now());
         int  result= roleMapper.insertSelective(role);
        log.info("create Role seccuss! result:{},role:{}",result,role);
            if (result == 1) return true;
            return false;
    }

    /**
     * 物理删除角色
     * @param id
     * @return
     */
    @Transactional
    public Boolean deleteRole(Long id) {
        //0.删除用户角色关系
        roleMapper.deleteUsersAndRole(id);
        //1.删除角色和菜单的关联关系
        //TODO
        //2. 删除角色本身
        int result = roleMapper.deleteByPrimaryKey(id);
        log.info("delete Role seccuss,roleId:{}, result: {}",id,result);
        if( result==1 )
            return true;
        return false;
    }
    //更新用户
    public Boolean updateRole(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        int result =  roleMapper.updateByPrimaryKeySelective(role);
        if(result==1)
            return true;
        return false;
    }

    /**
     * 检查角色名是否已经存在
     * @param name
     * @return
     */
    public Boolean check(String name){
        Role role = new Role();
        role.setName(name);
         int result = roleMapper.selectCount(role);
         log.info("check Role info：if result=1,role is exist  else role no exist . result:{} role_name:{} ",result,name);
       if(result==1) return true;
       return false;
    }

    public List<Role> queryRole() {
        return roleMapper.selectAll();
    }

    public List<Role> getRoleByUserId(Long userId) {
        //1、先通过用户id 查询该用户对应的角色ids，
          List<Long> ids = roleMapper.findRole(userId);
          log.info("getRoleByUserId查询到的角色IDS： "+ids.toString());
        //2、再通过角色ids 查询角色
        List<Role> roles = new ArrayList<Role>();
        for(Long id:ids){
            roles.add(roleMapper.selectByPrimaryKey(id));
        }
        log.info("RoleService查询到的角色： "+roles.toString());
        return  roles;
    }

    public Role findUser(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}

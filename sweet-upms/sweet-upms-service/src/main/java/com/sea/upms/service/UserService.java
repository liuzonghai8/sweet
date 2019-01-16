package com.sea.upms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.common.enums.ExceptionEnum;
import com.sea.common.exception.SweetException;
import com.sea.upms.mapper.UserMapper;
import com.sea.upms.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryUser(){
        return  userMapper.selectAll();
    }


    public PageInfo<User> queryUserByPage(Integer page, Integer rows,
                                             String sortBy, Boolean desc, String key) {
        PageHelper.startPage(page,rows);//开启分页
        //过滤查询条件
        Example example = new Example(User.class);
        if(StringUtils.isNotBlank(key)){
            log.info("查询条件为： " + key.toString());
            example.createCriteria().orLike("loginName","%"+ key +"%")
                    .orLike("realName","%" + key +"%")
            .orLike("phone" ,"%"+key + "%")
            ;
        }
        //排序条件
        if(StringUtils.isNotBlank(sortBy)){
            log.info(sortBy+" "+(desc? " DESC" : " ASC") + " 排序");
            example.setOrderByClause(sortBy+(desc ? " DESC" : " ASC"));
        }
        List<User> userList = userMapper.selectByExample(example);
//        if(CollectionUtils.isEmpty(userList)){
//            throw new SweetException(ExceptionEnum.USER_NOT_FOUD);
//        }
        return new PageInfo<>(userList);
    }

//添加用户
    public void addUser(User user) {
        userMapper.insert(user);
    }

    //物理删除
    public void deleteUser(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }
    //更新用户
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
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
}

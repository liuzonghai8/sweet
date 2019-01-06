package com.sea.upms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.upms.mapper.RoleMapper;
import com.sea.upms.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


@Service
@Slf4j
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

//    public List<User> queryUser(){
//        return  roleMapper.selectAll();
//    }


    public PageInfo<Role> queryRoleByPage(Integer page, Integer rows,
                                          String sortBy, Boolean desc, String key) {
        PageHelper.startPage(page,rows);//开启分页
        //过滤查询条件
        Example example = new Example(Role.class);
        if(StringUtils.isNotBlank(key)){
            log.info("查询条件为： " + key.toString());
            example.createCriteria().orLike("name","%"+ key +"%")
                    .orLike("code","%" + key +"%")
            .orLike("description" ,"%"+key + "%")
            ;
        }
        //排序条件
        if(StringUtils.isNotBlank(sortBy)){
            log.info(sortBy+" "+(desc? " DESC" : " ASC") + " 排序");
            example.setOrderByClause(sortBy+(desc ? " DESC" : " ASC"));
//            String sortByClause = sortBy + (desc ? " DESC" : " ASC");
//            log.info("sortByClause "+ sortByClause);
//            example.setOrderByClause(sortByClause);
        }
        return new PageInfo<>(roleMapper.selectByExample(example));
    }

//添加用户
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    //物理删除
    public void deleteRole(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }
    //更新用户
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

//    //判断用户登录名是否存在
//    public User exist(String loginName){
//       Example example = new Example(User.class);
//       log.info("参数为："+ loginName);
//       example.createCriteria()
//               .orEqualTo("loginName",loginName);
//       User user1 = roleMapper.selectOneByExample(example);
//       log.info("查询到的用户为："+user1);
//       //ToDo 需要完善
//        return null;
//    }
}

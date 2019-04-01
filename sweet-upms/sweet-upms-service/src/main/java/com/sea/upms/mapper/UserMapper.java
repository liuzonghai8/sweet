package com.sea.upms.mapper;


import com.sea.upms.pojo.Role;
import com.sea.upms.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Insert("insert into sys_user_role  values (#{uid},#{rid})")
    int saveUserRole(@Param("uid") Long uid, @Param("rid") Long rid);

    @Delete("delete from sys_user_role where user_id = #{uid} and role_id = #{rid}")
    void deleteUserRole(@Param("uid") Long userid, @Param("rid") Long roleid);

    //联合查询
    @Select("select id,name,code from sys_role as sr ,sys_user_role as sur  where sr.id=sur.role_id and user_id = #{uid}")
    List<Role> findRolesByUserId(@Param("uid") Long uid);
    //内连接查询
    @Select("SELECT id,name,code FROM sys_role INNER JOIN sys_user_role ON id = role_id WHERE user_id =  #{uid}")
    List<Role> findRolesByUser(@Param("uid") Long uid);
    @Select("SELECT role_id FROM sys_user_role WHERE user_id =  #{uid}")
    List<Long> finRoleIds(@Param("uid") Long uid);

    @Delete("DELETE FROM sys_user_role WHERE user_id = #{uid}")
    int deleteUserRoles(@Param("uid") Long uid);

}


package com.sea.upms.mapper;


import com.sea.upms.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    @Insert("insert into sys_user_role  values (#{uid},#{rid})")
    int saveUserRole(@Param("uid") Long uid, @Param("rid") Long rid);

    @Delete("delete from sys_user_role where user_id = #{uid} and role_id = #{rid}")
    void deleteUserRole(@Param("uid") Long userid, @Param("rid") Long roleid);
}


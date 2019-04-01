package com.sea.upms.mapper;


import com.sea.upms.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

  @Select("select role_id from sys_user_role where user_id = #{uid}")
  List<Long> findRole(@Param("uid") Long uid);

  @Delete("DELETE FROM sys_user_role WHERE role_id = #{rid}")
    int deleteUsersAndRole(@Param("rid") Long rid);
}


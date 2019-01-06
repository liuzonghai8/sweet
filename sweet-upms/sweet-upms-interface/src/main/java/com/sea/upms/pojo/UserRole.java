package com.sea.upms.pojo;

import lombok.Data;
import sun.rmi.runtime.Log;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "sys_user_role")
public class UserRole
{
    private Long userId;
    private Long roleId;
}

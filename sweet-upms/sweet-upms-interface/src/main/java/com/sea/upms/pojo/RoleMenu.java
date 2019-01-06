package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Data
@Table(name = "sys_role_menu")
@NameStyle(Style.camelhumpAndLowercase)
public class RoleMenu {

    private Long roleId;
    private Long menuId;
}

package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Data
@Table(name = "sys_user_department")
@NameStyle(Style.camelhumpAndLowercase)
public class UserDepartment {

    private Long userId;
    private Long departmentId;
    private String name;  //部门名称
    private String address; // 部门地址
    private String telphone; //部门电话
    private String code;//部门编号
}

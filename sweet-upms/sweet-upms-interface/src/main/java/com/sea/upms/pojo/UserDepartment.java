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
}

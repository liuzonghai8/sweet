package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name="sys_department")
public class Department {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name; //部门名称
    private String code; //部门编号
    private String description; //部门描述
    private Long parentId; //上级部门id
    private String address; //地址
    private String telphone; //部门电话
    private String enableTag; //启用标记

    @Transient
    private List<User> users;
}

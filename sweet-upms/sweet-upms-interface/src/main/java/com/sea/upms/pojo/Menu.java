package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "sys_menu")
public class Menu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String permission;
    private String frontPath;
    private String requestUrl;
    private String requestMethod;
    private Long parentId;
    private String icon;
    private String component;
    private String enableTag;
}

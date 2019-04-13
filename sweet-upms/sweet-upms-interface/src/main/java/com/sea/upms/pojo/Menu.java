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
    private Integer id;
    private String name; //名称
    private String permission;  // 权限
    private String frontPath; //前端路径
    private String requestUrl; //请求URL
    private String requestMethod; //请求方法
    private Integer parentId;  //上级id parent_id
    private String icon;  // 页面图标
    private String component; // Vue组件
    private Integer sort; //排序
    private  String type; //类型
    private String enableTag; // 是否使用标志
}

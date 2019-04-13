package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "sys_role")
public class Role {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String code;
    private String description;
    private String enableTag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

//    @Transient
//    private List<Menu> menus;

}

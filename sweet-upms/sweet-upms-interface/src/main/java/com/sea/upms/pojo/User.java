package com.sea.upms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "sys_user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private  String loginName;
    private  String realName;
    @JsonIgnore
    private String password;
    private  String phone;
    private String avatar;
    private String enableTag;
    @JsonIgnore
    private String salt; //私有盐
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String intro;
//    @Transient
//    private List<Role> roles; //用户对应的角色集合

}

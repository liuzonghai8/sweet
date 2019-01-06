package com.sea.upms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.management.relation.Role;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

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
    @Transient
    private List<Role> roles; //用户对应的角色集合

}

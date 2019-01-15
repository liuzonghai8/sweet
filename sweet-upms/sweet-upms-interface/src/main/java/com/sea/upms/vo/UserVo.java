package com.sea.upms.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;
    private  String loginName;
    private  String realName;
    private String password;
    private  String phone;
    private String avatar;
    private String enableTag;

    private List<Role> roles; //用户对应的角色集合
}

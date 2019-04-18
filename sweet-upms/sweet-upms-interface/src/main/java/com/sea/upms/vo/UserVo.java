package com.sea.upms.vo;

import com.sea.upms.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;
    private  String username;
    private  String realName;
    private  String phone;
    private String avatar;
    private String enableTag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String intro;

    private List<Role> roles; //用户对应的角色集合
}

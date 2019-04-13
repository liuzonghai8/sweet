package com.sea.upms.dto;

import com.sea.upms.pojo.User;
import lombok.Data;

import java.util.List;
@Data
public class UserDTO extends User {
    private List<Long> roles;

    private Integer deptId;

    /**
     * 新密码
     */
    private String newpassword;
}

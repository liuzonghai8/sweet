package com.sea.auth.entity;

import lombok.Data;

@Data
public class ResultAuth {
    private String token;
    private UserInfo userInfo;
}

package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    int id;
    String name;
    String phone;
}

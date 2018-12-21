package com.sea.upms.service;

import com.sea.upms.mapper.UserMapper;
import com.sea.upms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryUser(){
        return  userMapper.selectAll();
    }


}

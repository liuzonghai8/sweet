package com.sea.upms.service;

import com.sea.upms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpmsService {

    @Autowired
    private UserMapper userMapper;


}

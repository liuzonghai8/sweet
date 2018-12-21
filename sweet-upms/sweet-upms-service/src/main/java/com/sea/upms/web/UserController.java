package com.sea.upms.web;

import com.sea.upms.pojo.User;
import com.sea.upms.service.UpmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys")
public class UserController {

    @Autowired
    private UpmsService upmsService;

    @GetMapping("page")
    public User queryUserByPage(){
        User u = new User();
        u.setId(1);
        u.setUsername("ddd");
        u.setPhone("111111");
         return u;
    }


}

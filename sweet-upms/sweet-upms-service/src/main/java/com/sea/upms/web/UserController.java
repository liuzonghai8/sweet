package com.sea.upms.web;

import com.sea.upms.pojo.User;
import com.sea.upms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sys")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("page")
    public List<User> queryUserByPage(){
         return userService.queryUser();
    }

    @RequestMapping("/")
    public String index(){
        return "hello word";
    }


}

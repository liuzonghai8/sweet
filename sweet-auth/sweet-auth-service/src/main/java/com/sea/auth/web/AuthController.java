package com.sea.auth.web;

import com.sea.auth.properties.JwtProperties;
import com.sea.auth.service.AuthService;
import com.sea.common.utils.CookieUtils;
import com.sea.common.vo.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public ResultDTO<Void> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        String token;
        token = authService.login(username,password);
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge()).request(request).build(jwtProperties.getCookieName(), token);
        return new ResultDTO<>();

    }
}

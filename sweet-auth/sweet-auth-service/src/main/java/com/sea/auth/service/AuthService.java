package com.sea.auth.service;

import com.sea.auth.entity.UserInfo;
import com.sea.auth.properties.JwtProperties;
import com.sea.auth.utils.JwtUtils;
import com.sea.upms.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import com.sea.auth.client.UserClient;

/**
 * @author bystander
 * @date 2018/10/1
 */
@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties props;


    public String login(String username, String password) {
        try {
            User user = userClient.queryUser(username, password);
            log.info("查询到的用户为 {}",user);
            if (user == null) {
                return null;
            }
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            //生成Token
            String token = JwtUtils.generateToken(userInfo, props.getPrivateKey(), props.getExpire());
            log.info("----生成的token为：{}------",token);
            return token;
        } catch (Exception e) {
            log.error("【授权中心】用户名和密码错误，用户名：{}", username,e);
            throw new RuntimeException(e); //LyException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }
}

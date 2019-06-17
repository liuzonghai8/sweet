package com.sea.auth.service;

import com.sea.auth.entity.ResultAuth;
import com.sea.auth.entity.UserInfo;
import com.sea.auth.properties.JwtProperties;
import com.sea.auth.utils.JwtUtils;
import com.sea.upms.pojo.User;
import com.sea.upms.vo.UserVo;
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


    public ResultAuth login(String username, String password) {
        log.info("------前端传输过来的username:{}，AND Password：{}-------",username,password);
        try {
            User user = userClient.queryUser(username, password);
            log.info("从数据库查询到的用户为 {}",user);
            if (user == null) {
                return null;
            }
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            ResultAuth resultAuth = new ResultAuth();
            resultAuth.setUserInfo(userInfo);
            //生成Token
            String token = JwtUtils.generateToken(userInfo, props.getPrivateKey(), props.getExpire());
            log.info("----生成的token为：{}------",token);
           resultAuth.setToken(token);
            return resultAuth;
        } catch (Exception e) {
            log.error("【授权中心】用户名和密码错误，用户名：{}", username,e);
            throw new RuntimeException(e); //LyException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }

    public UserVo getUserInfo(Long userid) {
        return userClient.findUserAllInfo(userid);
    }
}

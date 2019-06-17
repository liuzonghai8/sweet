package com.sea.auth.web;

import com.sea.auth.entity.ResultAuth;
import com.sea.auth.entity.UserInfo;
import com.sea.auth.properties.JwtProperties;
import com.sea.auth.service.AuthService;
import com.sea.auth.utils.JwtUtils;
import com.sea.common.utils.CookieUtils;
import com.sea.common.vo.ResultDTO;
import com.sea.upms.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public ResultDTO<ResultAuth> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        
       // String token = authService.login(username,password);
        ResultAuth resultAuth = authService.login(username,password);
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge()).request(request).build(jwtProperties.getCookieName(), resultAuth.getToken());
        return new ResultDTO<>(resultAuth);

    }
    /**
     * 注销登录
     *
     * @param token "SWEET_TOKEN"
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue("SWEET_TOKEN") String token, HttpServletResponse response) {

       log.info("_-------logout -token:{}----------",token);
        if (StringUtils.isNotBlank(token)) {
            CookieUtils.newBuilder(response).maxAge(0).build(jwtProperties.getCookieName(), token);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


//    @GetMapping("/userInfo2/{id}")
//            // public ResultDTO<UserVo> getUserInfo(@PathVariable("id") Long userid,@CookieValue("SWEET_TOKEN") String token){
//    public ResultDTO<UserVo> getUserInfo2(@CookieValue("SWEET_TOKEN") String token,@PathVariable("id") Long userid){
//        log.info("token:");
//        log.info(token);
//        //从Token中获取用户信息
//        UserInfo userInfo = JwtUtils.getUserInfo(jwtProperties.getPublicKey(), token);
//        log.info("用户信息",userInfo);
////
//// UserVo userVo = new UserVo();
////        return new ResultDTO<>(userVo);
//         return new ResultDTO<>(authService.getUserInfo(userid));
//    }

    @GetMapping("/userInfo")
    public ResultDTO<UserVo> getUserInfo(@CookieValue("SWEET_TOKEN") String token){
        log.info(token);
        //从Token中获取用户信息
        UserInfo userInfo = JwtUtils.getUserInfo(jwtProperties.getPublicKey(), token);
        log.info(userInfo.getId().toString());

        return new ResultDTO<>(authService.getUserInfo(userInfo.getId()));
    }
}

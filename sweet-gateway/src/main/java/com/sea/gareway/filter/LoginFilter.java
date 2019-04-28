package com.sea.gareway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import com.sea.auth.utils.JwtUtils;
import com.sea.common.utils.CookieUtils;
import com.sea.gareway.properties.FilterProperties;
import com.sea.gareway.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断是否登录的过滤器以及是否token是否过期
 */
@Component
@EnableConfigurationProperties({JwtProperties.class,FilterProperties.class})
@Slf4j
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties properties;

    @Autowired
    private FilterProperties filterPro;

    //拦截类型 ,前置拦截
    @Override
    public String filterType() {
        log.info("filterType");
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        log.info("filterOrder");
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        log.info("shouldFilter");
        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("上下文ctx：{}",ctx);
        // 获取request
        HttpServletRequest req = ctx.getRequest();
        log.info("请求信息req.getCookies：{}",req.getCookies());
        // 获取路径
        String requestURI = req.getRequestURI();
        log.info("rul:{}",requestURI);
         //判断白名单
        return !isAllowPath(requestURI);
       // return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        log.info("run");
        RequestContext context = RequestContext.getCurrentContext();
        log.info("获取上下文context ：",RequestContext.getCurrentContext());

        // 获取request
        HttpServletRequest request = context.getRequest();
        log.info("httpServletRequest: ",request);
        // 获取token
        String token = CookieUtils.getCookieValue(request, this.properties.getCookieName());

        log.info("拦截到的token: ",token);
        // 校验
        try {
            // 校验通过什么都不做，即放行
            JwtUtils.getUserInfo(this.properties.getPublicKey(),token);
        } catch (Exception e) {
            // 校验出现异常，返回403
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }

    private boolean isAllowPath(String requestURI) {
        // 定义一个标记
        boolean flag = false;
        // 遍历允许访问的路径
        for (String path : this.filterPro.getAllowPaths() ) {
            // 然后判断是否是符合
            if(requestURI.startsWith(path)){
                flag = true;
                break;
            }
        }
        log.info("flag:{}",flag);
        return flag;
    }
}

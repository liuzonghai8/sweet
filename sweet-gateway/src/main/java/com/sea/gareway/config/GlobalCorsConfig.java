package com.sea.gareway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://www.sweet.com");
        config.addAllowedOrigin("http://api.sweet.com");
       // config.addAllowedOrigin("http://192.168.10.119");
        //config.addAllowedOrigin("http://192.168.1.119");
       // config.addAllowedOrigin("http://192.168.1.119:8080");
       // config.addAllowedOrigin("http://192.168.1.118");
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setMaxAge(3600L);
        // 4）允许的头信息 实际生产填写具体的头信息
       // config.addAllowedHeader("http://192.168.1.119:8080");
       // config.addAllowedHeader("http://192.168.1.119");
        //config.addAllowedHeader("Access-Control-Allow-Origin");
        config.addAllowedHeader("*");

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}



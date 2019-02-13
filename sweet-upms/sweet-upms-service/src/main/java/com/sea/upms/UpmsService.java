package com.sea.upms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@Slf4j
@MapperScan("com.sea.upms.mapper")
public class UpmsService {
    public static void main(String[] args) {
        SpringApplication.run(UpmsService.class);
        log.info("upmsService running");
    }
}

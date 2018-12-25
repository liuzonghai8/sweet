package com.sea.consult;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sea.consult.mapper")
@Slf4j
public class ConsultService {
    public static void main(String[] args) {
        SpringApplication.run(ConsultService.class);
        log.info("ConsultService 启动成功");
    }
}

package com.sea.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SweetRegistry {
    public static void main(String[] args) {
        SpringApplication.run(SweetRegistry.class, args);
    }
}

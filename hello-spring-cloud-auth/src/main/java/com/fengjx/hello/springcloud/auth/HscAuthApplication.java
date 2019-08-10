package com.fengjx.hello.springcloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fengjianxin
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HscAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HscAuthApplication.class, args);
    }

}

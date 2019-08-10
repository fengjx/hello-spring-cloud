package com.fengjx.hello.springcloud.user;

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
public class HscUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(HscUserApplication.class, args);
    }

}

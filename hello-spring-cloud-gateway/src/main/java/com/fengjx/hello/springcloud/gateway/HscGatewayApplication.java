package com.fengjx.hello.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fengjianxin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class HscGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HscGatewayApplication.class, args);
    }

}

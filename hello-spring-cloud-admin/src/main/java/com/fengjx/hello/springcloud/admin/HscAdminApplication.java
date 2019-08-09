package com.fengjx.hello.springcloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengjianxin
 */
@EnableAdminServer
@SpringBootApplication
public class HscAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HscAdminApplication.class, args);
    }
}
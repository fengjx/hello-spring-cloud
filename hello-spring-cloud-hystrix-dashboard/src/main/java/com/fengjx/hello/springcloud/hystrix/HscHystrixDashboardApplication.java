package com.fengjx.hello.springcloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author fengjianxin
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HscHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HscHystrixDashboardApplication.class, args);
    }

}

package com.fengjx.hello.springcloud.user.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fengjianxin
 */
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/info")
    public Object info() {
        return discoveryClient.getInstances(appName);
    }

    @RequestMapping("/hello")
    public Object hello() {
        return "hello: " + appName;
    }

    @RequestMapping("/openapi/ping")
    public Object ping() {
        return "pong";
    }

}

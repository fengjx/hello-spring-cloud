package com.fengjx.hello.springcloud.user.web;

import com.fengjx.hello.springcloud.user.api.AuthApi;
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

    @Resource
    private AuthApi authApi;

    @RequestMapping("/info")
    public Object info() {
        return discoveryClient.getInstances(appName);
    }

    @RequestMapping("/hello")
    public Object hello() {
        return "hello: " + appName;
    }

    @RequestMapping("/hello-auth")
    public Object helloAuth() {
        return authApi.hello();
    }

    @RequestMapping("/openapi/ping")
    public Object ping() {
        return "pong";
    }

}

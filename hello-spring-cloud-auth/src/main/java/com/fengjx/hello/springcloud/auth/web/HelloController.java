package com.fengjx.hello.springcloud.auth.web;

import com.fengjx.hello.springcloud.user.api.UserApi;
import com.fengjx.hello.springcloud.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fengjianxin
 */
@Slf4j
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private UserApi userApi;

    @RequestMapping("/hello")
    public Object hello() {
        return discoveryClient.getInstances(appName);
    }

    @RequestMapping("/openapi/ping")
    public Object ping() {
        return "pong";
    }

    @RequestMapping("/hello-user")
    public Object helloUser() {
        return userApi.hello();
    }

    @RequestMapping("/find-user")
    public User findUser(Long id) {
        log.info("find user: {}", id);
        return userApi.findById(id);
    }

}

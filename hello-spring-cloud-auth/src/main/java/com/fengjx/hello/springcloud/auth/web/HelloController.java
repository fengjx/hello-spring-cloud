package com.fengjx.hello.springcloud.auth.web;

import com.fengjx.hello.springcloud.user.api.UserApi;
import com.fengjx.hello.springcloud.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

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
    private RestTemplate loadBalancedRestTemplate;

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
        return loadBalancedRestTemplate.getForEntity("http://hsc-user/hello", String.class);
    }

    @RequestMapping("/find-user")
    public User findUser(Long id) {
        return userApi.findById(id);
    }

}

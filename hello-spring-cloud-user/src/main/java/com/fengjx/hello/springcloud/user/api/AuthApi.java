package com.fengjx.hello.springcloud.user.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author fengjianxin
 */
@Component
public class AuthApi {

    private static final String USER_URL = "http://hsc-auth/%s";

    @Resource
    private RestTemplate loadBalancedRestTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String hello() {
        return loadBalancedRestTemplate.getForObject(String.format(USER_URL, "hello"), String.class);
    }


    public String fallback() {
        return "fallback";
    }

}

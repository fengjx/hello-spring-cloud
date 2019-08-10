package com.fengjx.hello.springcloud.user.api;

import com.fengjx.hello.springcloud.user.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author fengjianxin
 */
public class UserApi {

    private static final String USER_URL = "http://hsc-user/%s";

    public UserApi(){
        System.out.println("========userApi==========");
    }

    @Resource
    private RestTemplate loadBalancedRestTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String hello() {
        return loadBalancedRestTemplate.getForObject(String.format(USER_URL, "hello"), String.class);
    }


    @HystrixCommand(fallbackMethod = "fallbackindById")
    public User findById(Long id) {
        return loadBalancedRestTemplate.getForObject(String.format(USER_URL, "findById?id={?}"), User.class, id);
    }

    public String fallback() {
        return "fallback";
    }

    public User fallbackindById(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

}


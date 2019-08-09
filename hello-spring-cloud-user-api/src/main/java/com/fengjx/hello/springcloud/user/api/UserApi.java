package com.fengjx.hello.springcloud.user.api;

import com.fengjx.hello.springcloud.commons.api.FallbackApi;
import com.fengjx.hello.springcloud.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fengjianxin
 */
@FeignClient(name = "hsc-uaer", fallback = FallbackApi.class)
public interface UserApi {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping("findById")
    public User findById(Long id);

}

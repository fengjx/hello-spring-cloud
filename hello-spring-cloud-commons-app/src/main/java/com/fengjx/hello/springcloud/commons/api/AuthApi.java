package com.fengjx.hello.springcloud.commons.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fengjianxin
 */
@FeignClient(name = "hsc-auth")
public interface AuthApi {

    @RequestMapping("/hello")
    Object hello();

}

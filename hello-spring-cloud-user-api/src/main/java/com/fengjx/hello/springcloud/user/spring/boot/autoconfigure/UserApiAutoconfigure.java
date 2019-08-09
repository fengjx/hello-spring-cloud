package com.fengjx.hello.springcloud.user.spring.boot.autoconfigure;

import com.fengjx.hello.springcloud.commons.api.spring.boot.autoconfigure.ApiAutoconfigure;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengjianxin
 */
@Configuration
@AutoConfigureAfter(ApiAutoconfigure.class)
@ConditionalOnProperty(name = "hsc.api.enabled", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = "com.fengjx.hello.springcloud.user.api")
public class UserApiAutoconfigure {

    public UserApiAutoconfigure(){
        System.out.println("UserApiAutoconfigure");
    }

}

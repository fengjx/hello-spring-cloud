package com.fengjx.hello.springcloud.user.api;

import com.fengjx.hello.springcloud.commons.api.ApiAutoconfigure;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengjianxin
 */
@Configuration
@AutoConfigureAfter(ApiAutoconfigure.class)
@ConditionalOnProperty(
        prefix = "hsc.api.user",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
@EnableCircuitBreaker
public class UserApiAutoconfigure {

    @Bean
    @ConditionalOnMissingBean
    public UserApi userApi() {
        return new UserApi();
    }

}

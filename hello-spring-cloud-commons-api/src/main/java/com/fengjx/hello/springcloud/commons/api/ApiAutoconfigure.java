package com.fengjx.hello.springcloud.commons.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author fengjianxin
 */
@Configuration
@ConditionalOnProperty(
        prefix = "hsc.api.load-balanced",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class ApiAutoconfigure {

    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }


}

package com.fengjx.hello.springcloud.commons.api.spring.boot.autoconfigure;

import com.fengjx.hello.springcloud.commons.api.FallbackApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengjianxin
 */
@Configuration
@ConditionalOnProperty(name = "hsc.api.enabled", havingValue = "true", matchIfMissing = true)
public class ApiAutoconfigure {

    @Bean
    @ConditionalOnMissingBean
    public FallbackApi fallbackApi() {
        return new FallbackApi();
    }

}

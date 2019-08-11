package com.fengjx.hello.springcloud.commons.apm.accesslog;

import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengjianxin
 */
@Configuration
@ConditionalOnProperty(
        prefix = "hsc.accesslog",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class AccessLoggingAutoConfiguration {

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new AccessLoggingHttpTraceRepositoryDecorator(new InMemoryHttpTraceRepository(),
                LoggerFactory.getLogger("hsc.access.logger"));
    }
}

package com.fengjx.hello.springcloud.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengjianxin
 */
@Configuration
@MapperScan("com.fengjx.hello.springcloud.user.mapper")
public class DbConfig {



}

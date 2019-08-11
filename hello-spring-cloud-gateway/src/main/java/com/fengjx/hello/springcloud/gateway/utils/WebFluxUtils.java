package com.fengjx.hello.springcloud.gateway.utils;

import com.fengjx.hello.springcloud.commons.utils.JsonUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author fengjianxin
 */
public class WebFluxUtils {

    public static Mono<Void> writeWith(ServerWebExchange exchange, Object data, HttpStatus httpStatus) {
        byte[] bytes = JsonUtil.toJson(data).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

}

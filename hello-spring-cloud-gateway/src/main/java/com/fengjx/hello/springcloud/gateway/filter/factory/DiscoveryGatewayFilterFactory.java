package com.fengjx.hello.springcloud.gateway.filter.factory;

import com.fengjx.hello.springcloud.commons.constant.App;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 * @author fengjianxin
 */
@Slf4j
@Component
public class DiscoveryGatewayFilterFactory extends AbstractGatewayFilterFactory {


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            RequestPath path = exchange.getRequest().getPath();
            // 获取路由
            Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
            if (route == null) {
                return chain.filter(exchange);
            }

            String service = getUriString(path);
            if (StringUtils.isBlank(service)) {
                throw new NotFoundException("can not found service for path: " + path);
            }

            // 重构URI
            URI newUri = UriComponentsBuilder.fromUriString(service).build().toUri();
            // 重构路由
            Route newRoute = Route.async().id(route.getId())
                    .uri(newUri)
                    .order(route.getOrder())
                    .asyncPredicate(route.getPredicate())
                    .filters(route.getFilters())
                    .build();
            exchange.getAttributes().put(GATEWAY_ROUTE_ATTR, newRoute);
            return chain.filter(exchange);
        };
    }

    /**
     * 根据请求路径获取服务名
     */
    private String getUriString(RequestPath path) {
        String value = path.value();
        if (StringUtils.startsWith(value, App.OPEN_API)) {
            String service = StringUtils.substringBetween(value, App.OPEN_API + "/", "/");
            return "lb://" + App.APP_PREFIX + service;
        } else if (StringUtils.startsWith(value, App.INNER_API)) {
            String service = StringUtils.substringBetween(value, App.INNER_API + "/", "/");
            return "lb://" + App.APP_PREFIX + service;
        }
        return "";
    }

}

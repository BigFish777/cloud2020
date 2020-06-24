package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/24
 * \* Time: 14:24
 * \* 判断请求中是否携带token（这里可以做oath验证）
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getPath().toString();
        log.info("path");
        if (path.equals("/get/token")){
            log.info("获取token，放行！");
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("Authorization");
        if (token == null){
            log.error("用户token为空，非法用户");
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response.setComplete();
        }
        log.info(token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

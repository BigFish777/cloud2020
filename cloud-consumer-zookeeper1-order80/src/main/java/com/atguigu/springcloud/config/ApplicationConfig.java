package com.atguigu.springcloud.config;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 13:41
 * \* Spring配置类
 */
@Configuration
public class ApplicationConfig {
    @Bean
    @LoadBalanced//开启Ribbon 负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

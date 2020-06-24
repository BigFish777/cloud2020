package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/15
 * \* Time: 15:50
 * \
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableFeignClients     //开启feign的支持
@EnableCircuitBreaker //服务降级
public class RunFeignHystrix80 {
    public static void main(String[] args) {
        SpringApplication.run(RunFeignHystrix80.class,args);
    }
}

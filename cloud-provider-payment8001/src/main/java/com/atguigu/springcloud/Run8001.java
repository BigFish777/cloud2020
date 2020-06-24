package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 11:36
 * \
 */
@SpringBootApplication
@EnableEurekaClient //表示这是一个服务提供着
@EnableDiscoveryClient //服务与发现
public class Run8001 {
    public static void main(String[] args) {
        SpringApplication.run(Run8001.class,args);
    }
}

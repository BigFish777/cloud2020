package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class Run8002 {
    public static void main(String[] args) {
        SpringApplication.run(Run8002.class,args);
    }
}

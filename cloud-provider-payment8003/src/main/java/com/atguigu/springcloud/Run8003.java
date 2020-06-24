package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 11:36
 * \
 */
@SpringBootApplication
@EnableDiscoveryClient //让注册中心能够发现，扫描到改服务
public class Run8003 {
    public static void main(String[] args) {
        SpringApplication.run(Run8003.class,args);
    }
}

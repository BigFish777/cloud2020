package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/9
 * \* Time: 10:37
 * \
 */
@SpringBootApplication
@EnableDiscoveryClient//（英文翻译能被发现的客户端）
public class Run8004 {
    public static void main(String[] args) {
        SpringApplication.run(Run8004.class,args);
    }
}

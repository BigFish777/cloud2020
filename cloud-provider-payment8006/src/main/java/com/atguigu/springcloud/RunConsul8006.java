package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/10
 * \* Time: 14:15
 * \
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RunConsul8006 {

    public static void main(String[] args) {
        SpringApplication.run(RunConsul8006.class,args);
    }
}



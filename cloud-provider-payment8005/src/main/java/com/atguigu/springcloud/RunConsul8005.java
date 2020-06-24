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
public class RunConsul8005 {

    public static void main(String[] args) {
        SpringApplication.run(RunConsul8005.class,args);
    }
}



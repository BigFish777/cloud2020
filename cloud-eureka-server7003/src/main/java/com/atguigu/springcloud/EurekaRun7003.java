package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 11:36
 * \
 */
@SpringBootApplication
@EnableEurekaServer //表示为服务注册中心
public class EurekaRun7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRun7003.class,args);
    }
}

package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/24
 * \* Time: 11:36
 */
@EnableEurekaClient
@SpringBootApplication
public class RunConfig3355 {
    public static void main(String[] args) {
        SpringApplication.run(RunConfig3355.class,args);
    }
}

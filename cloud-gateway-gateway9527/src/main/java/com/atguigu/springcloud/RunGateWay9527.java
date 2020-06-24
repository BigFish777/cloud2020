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
 * \
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableEurekaClient
public class RunGateWay9527 {
    public static void main(String[] args) {
        SpringApplication.run(RunGateWay9527.class,args);
    }
}

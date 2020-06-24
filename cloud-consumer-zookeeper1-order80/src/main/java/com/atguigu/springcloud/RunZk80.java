package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/10
 * \* Time: 14:49
 * \
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableDiscoveryClient
public class RunZk80 {
    public static void main(String[] args) {
        SpringApplication.run(RunZk80.class,args);
    }
}

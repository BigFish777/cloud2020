package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/15
 * \* Time: 15:50
 * \
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@EnableHystrixDashboard
public class RunDashboardHystrix9001 {
    public static void main(String[] args) {
        SpringApplication.run(RunDashboardHystrix9001.class,args);
    }
}

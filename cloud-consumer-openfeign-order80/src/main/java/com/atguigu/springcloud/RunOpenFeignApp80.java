package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/13
 * \* Time: 17:53
 * \* @EnableFeignClients 开启OpenFeign
 */
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//屏蔽数据源的扫描配置
@SpringBootApplication
@EnableFeignClients
public class RunOpenFeignApp80 {

    public static void main(String[] args) {
        SpringApplication.run(RunOpenFeignApp80.class,args);

        Map<String, Integer> map = new HashMap<>();
        map.put(null,1);
        map.put("2",2);
        map.put("3",3);



    }
}

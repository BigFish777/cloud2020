package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @version 1.0
 * @Author: xpH
 * @Description:
 * @Date: Created in 20:57 2021/1/25
 */

/**
 * Spring Cloud Stream是一个用于构建与共享消息传递系统相连的高度可扩展的事件驱动微服务的框架。
 * <p>
 * 该框架提供了基于已经建立和熟悉的Spring习语和最佳实践的灵活编程模型，包括对持久性Pub/sub（发布/订阅），使用者组和有状态分区的支持。
 * <p>
 * Spring Cloud Stream的核心构建块是：
 * <p>
 * 目标绑定器：负责提供与外部消息传递系统集成的组件。
 * 目标绑定：外部消息传递系统和应用程序之间的桥接消息的生产者和消费者（由目标绑定器创建）。
 * 消息：生产者和使用者使用的规范数据结构与目标绑定器（以及通过外部消息传递系统的其他应用程序）进行通信
 * @author 24267
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMqConsumer8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMqConsumer8802.class, args);
    }
}
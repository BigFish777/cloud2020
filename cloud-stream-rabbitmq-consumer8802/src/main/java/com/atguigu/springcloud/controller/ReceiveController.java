package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * @version 1.0
 * @Author: xpH
 * @Description:
 * @Date: Created in 12:37 2021/1/26
 */
@EnableBinding(Sink.class)
@Controller
public class ReceiveController {

    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者1号，---->接收的消息是:" + message.getPayload() + "\t端口号：" + serverPort);
    }
}

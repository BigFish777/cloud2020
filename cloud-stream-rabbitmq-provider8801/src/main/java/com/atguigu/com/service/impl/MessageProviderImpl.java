package com.atguigu.com.service.impl;

import com.atguigu.com.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @version 1.0
 * @Author: xpH
 * @Description:
 * @Date: Created in 21:00 2021/1/25
 */
@EnableBinding(Source.class) // 定义消息推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;

    /**
     * 发送消息
     */
    @Override
    public String send() {
        // 定义消息
        String serial = UUID.randomUUID().toString();
        //发送消息
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println(serial);
        return serial;
    }
}

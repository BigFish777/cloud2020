package com.atguigu.com.controller;

import com.atguigu.com.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @Author: xpH
 * @Description:
 * @Date: Created in 21:04 2021/1/25
 */
@RestController
public class MessageController {


    @Resource
    private IMessageProvider messageProvider;

    /**
     * 发送消息
     * @return
     */
    @GetMapping("/send/msg")
    public String sendMsg(){
        messageProvider.send();
        return "ok";
    }
}

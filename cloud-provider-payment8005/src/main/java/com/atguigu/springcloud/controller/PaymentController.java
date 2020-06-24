package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 12:46
 * \
 */
@Slf4j
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/uuid")
    public CommonResult<String> getPaymentById(){
        log.info("进入/payment/uuid");
        return new CommonResult(200,"查询成功,ConsulServerPort:"+serverPort, UUID.randomUUID().toString());
    }
}

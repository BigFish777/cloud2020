package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Parameter;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 13:38
 * \
 */
@Slf4j
@RestController
public class OrderController {
//    定义服务层的地址前缀
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get")
    public CommonResult<Parameter> getPayment(){
        log.info("consumer:查询<Payment>成功!");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/uuid",CommonResult.class);
    }
}

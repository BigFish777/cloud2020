package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Parameter;
import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/13
 * \* Time: 18:03
 * \
 */
@RestController
public class PaymentFeignController {

    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping("/payment/create")
    public CommonResult createPayment(){
        return paymentFeignService.createPayment(new Payment(null, UUID.randomUUID().toString()));
    }

    @GetMapping("/consumer/paymentFeign/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public CommonResult getPaymentTimeout(){
        return paymentFeignService.getPaymentTimeout();
    }

}

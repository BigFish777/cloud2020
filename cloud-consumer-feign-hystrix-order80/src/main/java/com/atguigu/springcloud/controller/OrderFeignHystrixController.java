package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/15
 * \* Time: 15:54
 * \
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "getPaymentTimeoutReserve")
public class OrderFeignHystrixController {
    @Resource
    PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentFeignHystrixService.getPaymentById(id);
    }

//    @HystrixCommand(fallbackMethod = "getPaymentTimeoutReserve",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    @GetMapping("/consumer/payment/timeout")
    public CommonResult getPaymentTimeout(){
        return paymentFeignHystrixService.getPaymentTimeout();
    }


    /**
     * 用来降级的备胎方法
     */
    public CommonResult getPaymentTimeoutReserve(){
        log.info("进入全局备胎服务---成功降级！");
        return CommonResult.success(Thread.currentThread().getName()+"--全局备胎启动.start---(┬＿┬)",null);
    }

}

package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description
 * @author: 土豆
 * @create: 2020-06-15 15:52
 **/
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentFeignHystrixService {
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public CommonResult getPaymentTimeout();

    @GetMapping("/payment/timeout_bt")
    public CommonResult getPaymentTimeoutbt();
}

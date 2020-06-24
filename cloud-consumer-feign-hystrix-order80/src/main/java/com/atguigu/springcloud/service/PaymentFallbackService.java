package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/16
 * \* Time: 11:30
 * \
 */
@Component
public class PaymentFallbackService implements PaymentFeignHystrixService {
    @Override
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(200,"getPaymentById:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }

    @Override
    public CommonResult getPaymentTimeout() {
        return new CommonResult(200,"getPaymentTimeout:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }

    @Override
    public CommonResult getPaymentTimeoutbt() {
        return new CommonResult(200,"getPaymentTimeoutbt:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }
}

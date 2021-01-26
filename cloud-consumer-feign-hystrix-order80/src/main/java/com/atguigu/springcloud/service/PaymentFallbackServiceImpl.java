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
public class PaymentFallbackServiceImpl implements PaymentFeignHystrixService {
    @Override
    public CommonResult getPaymentById(Long id) {
        return CommonResult.error("getPaymentById:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }

    @Override
    public CommonResult getPaymentTimeout() {
        return CommonResult.error("getPaymentTimeout:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }

    @Override
    public CommonResult getPaymentTimeoutbt() {
        return CommonResult.error("getPaymentTimeoutbt:(┬＿┬)备胎别抛弃了，变成千斤顶了。");
    }
}

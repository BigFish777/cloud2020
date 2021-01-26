package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 17:15
 */
@Slf4j
@Component
public class OrderConsumerServiceFallbackImpl implements OrderConsumerService{
    @Override
    public CommonResult getOrderList() {
        log.error("调用OrderService的查询订单列表服务 失败--降级");
        return CommonResult.error("调用OrderService的查询订单列表服务 失败--降级");
    }

    @Override
    public CommonResult createOrderList(Order order) {
        log.error("调用OrderService的创建订单服务 失败--降级 order:[{}]",order);
        return CommonResult.error("调用OrderService的创建订单服务 失败--降级");
    }
}

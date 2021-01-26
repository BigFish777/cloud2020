package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 17:15
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = OrderConsumerServiceFallbackImpl.class)
public interface OrderConsumerService {

    /**
     * 获取订单列表
     * @return
     */
    @GetMapping("/order/get/list")
    public CommonResult getOrderList();

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/order/create")
    public CommonResult createOrderList(@RequestBody Order order);
}

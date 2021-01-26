package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entity.dto.Order;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 16:15
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 获取订单列表
     * @return
     */
    @GetMapping("/get/list")
    public CommonResult getOrderList(){
       return orderService.getOrderList();
    }

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public CommonResult createOrderList(@RequestBody Order order){
        return orderService.createOrderList(order);
    }
}

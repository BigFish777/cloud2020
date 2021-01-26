package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.dto.Order;
import com.atguigu.springcloud.service.OrderConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 17:22
 */
@RestController
@RequestMapping("consumer/order")
public class OrderConsumerController {
    @Resource
    OrderConsumerService orderConsumerService;

    @Resource
    RestTemplate restTemplate;

    /**
     * 使用的openFeign访问微服务接口
     * 获取订单列表
     * @return
     */
    @GetMapping("/get/list")
    public CommonResult getOrderList(){
        CommonResult result = orderConsumerService.getOrderList();
        List data = (List)result.getData();
        result.setData(data);
        return result;
    }

    /**
     * 使用 RestTemplate访问微服务接口 使用 Ribbon作为负载均衡
     * 获取订单列表
     * @return
     */
    @HystrixCommand(fallbackMethod = "getOrderList2Fallback")
    @GetMapping("/get/list2")
    public CommonResult getOrderList2(){
        String url = "http://CLOUD-PAYMENT-HYSTRIX-SERVICE/order/get/list";
        CommonResult result = restTemplate.getForObject(url, CommonResult.class);
        return result;
    }
    public CommonResult getOrderList2Fallback(){
        return CommonResult.error("出错了进入降级服务");
    }

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public CommonResult createOrderList(@RequestBody Order order){
        return orderConsumerService.createOrderList(order);
    }

}

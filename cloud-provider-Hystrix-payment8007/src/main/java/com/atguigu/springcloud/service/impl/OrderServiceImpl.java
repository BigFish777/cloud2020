package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.enums.HytrixEnum;
import com.atguigu.springcloud.entity.dto.Order;
import com.atguigu.springcloud.mapper.OrderMapper;
import com.atguigu.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 16:13
 */
@Service
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallbackMethod")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    /**
     * 获取订单列表
     * @return
     */
    @HystrixCommand(commandProperties = {
         @HystrixProperty(name =HytrixEnum.ENABLED ,value = "true"),//是否开启断路器
         @HystrixProperty(name =HytrixEnum.REQUEST_VOLUME ,value = "10"),//请求次数
         @HystrixProperty(name =HytrixEnum.ENABLED ,value = "10000"),//窗口时间
         @HystrixProperty(name =HytrixEnum.ENABLED ,value = "60")//失败率达到多少跳闸
    })
    @Override
    public CommonResult getOrderList(){
        log.info("查询订单列表");
        List<Order> orders = orderMapper.selectList(null);
        return CommonResult.success("",orders);

    }

    /**
     * 创建订单
     * @return
     */
    @HystrixCommand
    @Override
    public CommonResult createOrderList(Order order){
        order.setOrderCode(IdUtil.randomUUID());
        log.info("创建订单:[{}]",order);
        int insert = orderMapper.insert(order);
        if (insert>0){
            log.info("创建订单成功");
            return CommonResult.success("创建订单成功",insert);
        }
        log.error("创建订单失败");
        return CommonResult.error("创建订单失败");
    }

    public CommonResult defaultFallbackMethod(){
        log.error("[OrderService] 的服务出现异常出发了默认降级方法");
        return CommonResult.error("服务出现问题了");
    }
}

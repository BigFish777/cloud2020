package com.atguigu.springcloud.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entity.dto.Order;
import com.atguigu.springcloud.mapper.OrderMapper;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import javafx.beans.DefaultProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 15:07
 */
public interface OrderService {

    /**
     * 获取订单列表
     * @return
     */
    public CommonResult getOrderList();

    /**
     * 创建订单
     * @return
     */
    public CommonResult createOrderList(Order order);


}

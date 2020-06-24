package com.atguigu.springcloud.service;

import org.apache.ibatis.annotations.Param;
import com.atguigu.springcloud.entities.Payment;

/**
 * @description
 * @author: 土豆
 * @create: 2020-06-03 12:44
 **/
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id")Long id);
}

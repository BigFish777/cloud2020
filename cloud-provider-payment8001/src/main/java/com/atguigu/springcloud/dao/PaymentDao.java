package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.atguigu.springcloud.entities.Payment;

/**
 * @description
 * @author: 土豆
 * @create: 2020-06-03 12:00
 **/
@Mapper
public interface PaymentDao {
    int create(Payment payment);
    Payment getPaymentById(@Param("id")Long id);
}

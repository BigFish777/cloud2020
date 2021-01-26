package com.atguigu.springcloud.entities.dto;

import lombok.Data;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 15:12
 */
@Data
public class Order {
    private Long id;
    private String orderCode;
    private Double orderPic;
    private String orderDesc;
    private Long commodityId;
}

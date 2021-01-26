package com.atguigu.springcloud.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 15:48
 */
@NoArgsConstructor
@Data
@TableName("td_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderCode;
    private Double orderPic;
    private String orderDesc;
    private Long commodityId;

    public Order(Long id, String orderCode, Double orderPic, String orderDesc, Long commodityId) {
        this.id = id;
        this.orderCode = orderCode;
        this.orderPic = orderPic;
        this.orderDesc = orderDesc;
        this.commodityId = commodityId;
    }
}


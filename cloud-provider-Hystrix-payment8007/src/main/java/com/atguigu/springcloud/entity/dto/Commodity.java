package com.atguigu.springcloud.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 15:13
 */
@Data
@TableName("td_commodity")
public class Commodity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Double pic;
}

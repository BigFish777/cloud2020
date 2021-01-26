package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entity.dto.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 15:53
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}

package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entity.dto.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/7 15:54
 */
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
}

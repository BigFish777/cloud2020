package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 11:52
 * \
 */
@Data
@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
public class Payment implements Serializable {
    private Long id;
    private String serial;
}

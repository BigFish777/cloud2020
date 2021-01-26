package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 11:56
 * \* Json返回体
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult {
    private Integer code;
    private String message;
    private Object data;

    public static CommonResult success(String message, Object data){
        return new CommonResult(200,message,data);
    }

    public static CommonResult error(String message){
        return new CommonResult(500,message);
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

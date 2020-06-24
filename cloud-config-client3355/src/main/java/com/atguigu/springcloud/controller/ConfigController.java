package com.atguigu.springcloud.controller;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/24
 * \* Time: 17:29
 * \
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String StrInfo;

    @GetMapping("/get/str")
    public String getStrInfo(){
        return StrInfo;
    }
}

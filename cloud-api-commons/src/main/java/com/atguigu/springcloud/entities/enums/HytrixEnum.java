package com.atguigu.springcloud.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/15 17:14
 */
public interface HytrixEnum {

    public final String ENABLED = "circuitBreaker.enabled";
    public final String REQUEST_VOLUME = "circuitBreaker.requestVolumeThreshold";
    public final String SLEEP_MILLIS = "circuitBreaker.sleepWindowInMilliseconds";
    public final String ERROR_PRE = "circuitBreaker.errorThresholdPercentage";
//    //是否开启断路器
//    ENABLED("enable","circuitBreaker.enabled"),
//    REQUEST_VOLUME("request_number","circuitBreaker.requestVolumeThreshold"),
//    SLEEP_MILLIS("timeout","circuitBreaker.sleepWindowInMilliseconds"),
//    ERROR_PRE("error","circuitBreaker.errorThresholdPercentage");
//
//
//    private final String name;
//    private final String value;
//
//    private HytrixEnum(String name, String value) {
//        this.name = name;
//        this.value = value;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public String getValue() {
//        return value;
//    }

}

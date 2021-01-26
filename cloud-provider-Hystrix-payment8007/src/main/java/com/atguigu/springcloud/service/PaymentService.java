package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/15
 * \* Time: 16:26
 * \
 */
@Service
@Slf4j
public class PaymentService {

    public CommonResult getPaymentById(@PathVariable("id") Long id){
        log.info(Thread.currentThread().getName()+"访问：/payment/get/"+id);
        return CommonResult.success(Thread.currentThread().getName()+"payment_ok,id:"+id,null);
    }


//    @HystrixCommand(fallbackMethod = "getPaymentTimeoutReserve",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    public CommonResult getPaymentTimeout(){
        log.info("进入PaymentTimeout");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 1/0;
        return CommonResult.success(Thread.currentThread().getName()+"payment_timeout",null);
    }

    /**
     * 用来降级的备胎方法
     * @return
     */
    public CommonResult getPaymentTimeoutReserve(){
        log.info("进入备胎服务---成功降级！");
        return CommonResult.success(Thread.currentThread().getName()+"--备胎启动.start---(┬＿┬)",null);
    }


    public CommonResult getPaymentTimeoutBT(){
        log.info("进入PaymentTimeout备胎服务！");
        return CommonResult.success(Thread.currentThread().getName()+"payment_timeout_bt",null);
    }

    /****服务熔断****/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少，跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        log.info("进入 PaymentCircuitBreaker");
        if (id<0){
            throw new RuntimeException("id 不能为负数！");
        }
        return Thread.currentThread().getName()+"流水号："+ IdUtil.simpleUUID();
    }

    /**
     * PaymentCircuitBreaker的降级服务
     */
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id){
        return "id 不能为负数，请稍后重试(┬＿┬)！";
    }

}

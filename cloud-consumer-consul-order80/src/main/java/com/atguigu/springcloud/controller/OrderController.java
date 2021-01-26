package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.annotation.Retention;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 13:38
 * \
 */
@Slf4j
@RestController
public class OrderController {
//    定义服务层的地址前缀
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/get")
    public CommonResult getPayment(){
        log.info("consumer:查询<Payment>成功!");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/uuid",CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity")
    public CommonResult getPayment2(){
        log.info("consumer:查询<getPayment2>成功!");
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/uuid", CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
           return forEntity.getBody();
        }else {
            return CommonResult.error("操作失败");
        }
    }

    @GetMapping("/consumer/get/lb")
    public String getlb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/uuid",String.class);
    }


}

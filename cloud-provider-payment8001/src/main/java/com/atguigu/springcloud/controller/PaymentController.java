package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/3
 * \* Time: 12:46
 * \
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }
        return new CommonResult(444,"插入数据库失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }
        return new CommonResult(444,"查询数据为空",null);
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获得所有的服务Id
        List<String> services = discoveryClient.getServices();
        for (String serviceId:services
             ) {
            log.info(">>>>>>>服务id:"+serviceId+"<<<<<<<");
            //根据服务id获得该服务下的实现，因为若果是集群会有多个实现
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance in:instances
            ) {
                //拿到每个服务实现 获得服务的
                log.info(in.getServiceId() + "\t" + in.getHost() + "\t" + in.getPort() + "\t" + in.getUri());
                /*
                 *   >>>>>>>服务id:cloud-consumer-order<<<<<<<
                 *      CLOUD-CONSUMER-ORDER
                 *          192.168.101.80	80	http://192.168.101.80:80
                 *  >>>>>>>服务id:cloud-payment-service<<<<<<<
                 *      CLOUD-PAYMENT-SERVICE
                 *          192.168.101.80	8002	http://192.168.101.80:8002
                 *          192.168.101.80	8001	http://192.168.101.80:8001
                 *
                 */
            }
        }
        return this.discoveryClient;
    }


    @GetMapping("/payment/lb")
    public CommonResult getPaymentlb(){
        return new CommonResult<String>(200,"成功","8001");
    }

    @GetMapping("/payment/timeout")
    public CommonResult getPaymentTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<String>(200,"成功",null);
    }

    @GetMapping("/get/token")
    public CommonResult getToken(){
        return new CommonResult<String>(200,"成功", UUID.randomUUID().toString());
    }
}

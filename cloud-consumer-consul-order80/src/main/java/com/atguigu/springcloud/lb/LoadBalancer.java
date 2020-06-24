package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @description
 * @author: 土豆
 * @create: 2020-06-13 16:04
 **/
public interface LoadBalancer {

    /**
     * 获得consul集群数量上的服务总数
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}

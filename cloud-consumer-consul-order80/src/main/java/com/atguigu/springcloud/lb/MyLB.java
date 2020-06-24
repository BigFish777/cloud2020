package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 一颗小土豆
 * \* Date: 2020/6/13
 * \* Time: 16:06
 * \* 自定义一个轮询算法 需要注释掉@LoadBalanced注解
 */
@Component
public class MyLB implements LoadBalancer {

    //原子类 Integer
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获得当前是第几次访问
    public final int getAndIncrement(){
        int currnet;
        int next;
        do {
            currnet = this.atomicInteger.get();
            next = currnet >= Integer.MAX_VALUE ? 0:currnet+1;
        }while (!this.atomicInteger.compareAndSet(currnet,next));
        System.out.println("********************** 第几次访问"+next+" next");
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //求获得下标
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}

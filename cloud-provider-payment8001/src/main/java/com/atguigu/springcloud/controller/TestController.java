package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Shop;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/1 16:39
 */
@RestController
@CrossOrigin
public class TestController {


    @GetMapping("payment/api/get/data")
    public CommonResult getDate(String type, Integer page, Integer size){
       dataService(type, page, size);
        return new CommonResult();
    }

    public List dataService(String type, Integer page, Integer size){
        Map<String, Object> map = new HashMap<>();
        ArrayList<Shop> shops = new ArrayList<>();
        if ("1".equals(type)){
            System.out.println("查询优质服务主题");
            for (int i = ((page-1)*size); i < (page*size); i++) {
                int serviceNum = (int)(Math.random()*100+1);//服务人数
                double address = (Math.random()*2+1);//距离
                int satr = (int) ((Math.random()*5)+1);//星星
                shops.add(new Shop(i,"桐乡市哈吉粮油农村合作社"+i,"桐乡市"+i+5+"镇村",address,serviceNum,satr));
            }
        }else if ("2".equals(type)){
            System.out.println("查询平均TOP3");
            for (int i = ((page-1)*size); i < (page*size); i++) {
                int serviceNum = (int)(Math.random()*100+1);//服务人数
                double address = (Math.random()*2+1);//距离
                int satr = (int) ((Math.random()*5)+1);//星星
                shops.add(new Shop(i,"桐乡市哈吉粮油农村合作社"+i,"桐乡市"+i+5+"镇村",address,serviceNum,satr));
            }
        }else if ("3".equals(type)){
            System.out.println("查询服务次数TOP3");
            for (int i = ((page-1)*size); i < (page*size); i++) {
                int serviceNum = (int)(Math.random()*100+1);//服务人数
                double address = (Math.random()*2+1);//距离
                int satr = (int) ((Math.random()*5)+1);//星星
                shops.add(new Shop(i,"桐乡市哈吉粮油农村合作社"+i,"桐乡市"+i+5+"镇村",address,serviceNum,satr));
            }
        }else {
            System.out.println("查询不到该类型");
        }
//        map.put("data",shops);
//        map.put("code",200);
//        map.put("msg","获取数据成功");
        return shops;
    }


    public static void main(String[] args) {
        int i = (int) ((Math.random()*100)+1);
        System.out.println(i);

        double q = (Math.random()*2+1);
        System.out.println(String.format("%.2f", q).toString());

        int b = (int) ((Math.random()*5)+1);
        System.out.println(b);
    }
}

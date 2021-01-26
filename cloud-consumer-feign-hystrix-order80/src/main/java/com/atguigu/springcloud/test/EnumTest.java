package com.atguigu.springcloud.test;

import com.atguigu.springcloud.entities.TestEnum;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/5 23:19
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(TestEnum.DAWANG);
        System.out.println(TestEnum.DAWANG.getName());
        System.out.println(TestEnum.DAWANG.getAge());
        //返回全部的枚举数组
        TestEnum[] values = TestEnum.values();
        for (int i = values.length - 1; i >= 0; i--) {
            System.out.println(values[i]);
            System.out.println(values[i].getAge());
            System.out.println(values[i].getName());
        }
        //根据枚举名返回枚举对象
        System.out.println(TestEnum.valueOf("XIAOMING"));
    }
}

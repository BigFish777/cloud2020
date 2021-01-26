package com.atguigu.springcloud.entities;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/5 23:07
 */
public enum TestEnum {
    /**
     * 提供当前对象的枚举类，多个之间用 “,” 隔开，末尾用分号 “;”结束
     */
    XIAOMING("小明","24"),
    DAWANG("大王","15"),
    HONGHONG("红红","18");

    //生命对象属性使用private final修饰
    private final String name;
    private final String age;

    //私有化构造器，并给对象赋值
    private TestEnum(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}

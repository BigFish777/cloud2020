package com.atguigu.springcloud.entities;

/**
 * @Author @我是一颗小土豆
 * @Date 2020/11/1 17:29
 */
public class Shop {
    private int id;//id
    private String name;//名称
    private String address;//地址
    private double distance;//距离
    private Integer serviceTimes;//服务次数
    private Integer star;//星级

    public Shop() {
    }

    public Shop(int id, String name, String address, double distance, Integer serviceTimes, Integer star) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.serviceTimes = serviceTimes;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getServiceTimes() {
        return serviceTimes;
    }

    public void setServiceTimes(Integer serviceTimes) {
        this.serviceTimes = serviceTimes;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}

package com.chinaliyq.userapi.bean;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/25 - 11:20
 * @Version: 1.0
 **/
public class Person {
    int id;
    String name;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}

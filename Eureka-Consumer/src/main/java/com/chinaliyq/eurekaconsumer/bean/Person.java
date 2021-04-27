package com.chinaliyq.eurekaconsumer.bean;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/24 - 15:49
 * @Version: 1.0
 **/
public class Person {
    private int id;
    private String name;

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

    public Person() {
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(int id) {
        this.id = id;
    }
}

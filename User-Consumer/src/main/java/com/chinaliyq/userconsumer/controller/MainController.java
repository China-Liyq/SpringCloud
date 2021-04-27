package com.chinaliyq.userconsumer.controller;

import com.chinaliyq.userapi.bean.Person;
import com.chinaliyq.userconsumer.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/24 - 18:29
 * @Version: 1.0
 **/
@RestController
public class MainController {
    @Value("${server.port}")
    String port;
//    @Autowired
//    UserApi api;
    @Autowired
    ConsumerApi api;

//    @GetMapping("/alive")
//    public String alive(){
//        return api.alive();
//    }

    @GetMapping("/register")
    public String register(){
        return api.register();
    };
    @GetMapping("/alive")
    public String alive() {
        /**
         * URL 不能变
         *
         * jar
         * 文档
         */
        return api.alive();
    }

//    @GetMapping("/vip")
//    public String vip() {
//
//        return mapi.getVip();
//    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return api.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id,String name) {
        System.out.println(id);
        return api.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
//		System.out.println(map);
//		map = new HashMap<>(2);
//
//		map.put("id", "444");
//		map.put("name", "中国");
//		syso
        System.out.println(map);
        return api.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return api.postMap(map);
    }


    @GetMapping("/postPerson")
    public Person postPserson(@RequestParam Map<String, Object> map){
        System.out.println("初始：" + map);
        Person person = new Person();
        person.setId(Integer.parseInt(map.get("id").toString()));
        person.setName("xxx");
        System.out.println("赋值："+ person);
        return api.postPerson(person);
    };
}

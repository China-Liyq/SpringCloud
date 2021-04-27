package com.chinaliyq.userpovider.controll;

import com.chinaliyq.userapi.UserAPI;
import com.chinaliyq.userapi.bean.Person;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: liyq
 * @Description: eureka-server
 * @Date: 2021/4/24 - 18:08
 * @Version: 1.0
 **/
@RestController
public class UserController implements UserAPI {
    @RequestMapping("/alive")
    public String getAlive(){
        return "ok111";
    }
//    @Override
//    public String alive() {
//        return "alive666555";
//    }

    @Override
    public String register() {
        return "register222212311";
    }

    @Override
    public String getById(int id) {
        String str ="abc--:" + id;
        return str;
    }

    @Value("${server.port}")
    String port;


    private AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {

        try {
            System.out.println("准备睡");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = count.getAndIncrement();
        System.out.println("====好的第：" + i + "次调用");
        return "port:" + port;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        System.out.println(id);
        return Collections.singletonMap(id, "mmeme");
    }

    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id,String name) {
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }
    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        System.out.println("***:"+map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

//    @PostMapping("/postPerson")
//    public Map<Integer, String> postPerson1(@RequestBody Person person) {
//        System.out.println(ToStringBuilder.reflectionToString(person));
//        return Collections.singletonMap(person.getId(), person.getName());
//    }
    @Override
    public Person postPerson(Person person) {
        System.out.println(person);
        return person;
    }



}

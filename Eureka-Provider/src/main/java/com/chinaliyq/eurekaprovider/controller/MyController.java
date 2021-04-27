package com.chinaliyq.eurekaprovider.controller;

import com.chinaliyq.eurekaprovider.bean.Person;
import com.chinaliyq.eurekaprovider.service.HealthStatusService;
import com.sun.javafx.logging.PulseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/21 - 16:44
 * @Version: 1.0
 **/
@RestController
public class MyController {
    @Value("${server.port}")
    String port;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/gethi")
    public String getHi(){
        return "-hello world-!,port:" + port;
    }

    @RequestMapping("/getMap")
    public Map<String,String> getMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","xy123");
        return map;
    }

    @GetMapping("/getObj")
    public Person getObj() {
        Person person = new Person();
        person.setId(100);
        person.setName("xiaoming");
        return person;
    }

    @GetMapping("/getObj1")
    public Person getObj(String name) {
        Person person = new Person(11,name);
        return person;
    }

    @PostMapping("/postParam")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws Exception {
        System.out.println(11);
        URI uri = new URI("https://www.baidu.com/s?wd=" + person.getName());
        System.out.println(uri);
        response.addHeader("Location", uri.toString());
//        response.sendRedirect(uri.toString());
        return uri;
    }




    @Autowired
    HealthStatusService healthStatusSrv;

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        healthStatusSrv.setStatus(status);
        return healthStatusSrv.getStatus();
    }

}

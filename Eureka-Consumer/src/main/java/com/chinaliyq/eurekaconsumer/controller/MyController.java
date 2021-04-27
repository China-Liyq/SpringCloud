package com.chinaliyq.eurekaconsumer.controller;

import com.chinaliyq.eurekaconsumer.bean.Person;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.bouncycastle.pqc.crypto.rainbow.RainbowSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/21 - 17:30
 * @Version: 1.0
 **/
@RestController
public class MyController {
    @Value("${server.port}")
    String port;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    EurekaClient eurekaClient;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;



    @RequestMapping("/gethi")
    public String getHi(){
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        System.out.println("instances---:" + instances);
        int order = discoveryClient.getOrder();
        System.out.println("order:" + order);
        List<String> services = discoveryClient.getServices();
        System.out.println("services:" + services);
        return "hi--1,port:"+ port;
    }


    @RequestMapping("/getpm")
    public String getProviderMethod(){
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        System.out.println("instances---:" + instances);
        if (instances.size()>0){
            ServiceInstance instance = instances.get(0);
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/gethi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(url, String.class);
            System.out.println("方法值:" + forObject);
        }
        return "hi-444";
    }


    @RequestMapping("/getpm2")
    public String getProviderMethod2(){
        List<InstanceInfo> instanceInfos = eurekaClient.getInstancesByVipAddress("provider", false);
        if (instanceInfos.size() > 0){
            final InstanceInfo instance = instanceInfos.get(0);
            System.out.println("instances---:" + instance);
            System.out.println(instance.getStatus());
            String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/gethi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(url, String.class);
            System.out.println("方法值:" + forObject);
        }
        return "hi-444";
    }

    @RequestMapping("/getpm3")
    public String getProviderMethod3(){
        ServiceInstance instance = loadBalancerClient.choose("provider");
        System.out.println("instances---:" + instance);
        if(null != instance){
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/gethi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(url, String.class);
            System.out.println("方法值:" + forObject);
            return forObject + "hi-333" + url;
        }
        return "11";
    }


    @RequestMapping("/getpm4")
    public String getRound(){
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        System.out.println("instances---:" + provider);
        if(provider.size() > 0){
            int randomIndex = new Random().nextInt(provider.size());
            ServiceInstance instance = provider.get(randomIndex);
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/gethi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(url, String.class);
            System.out.println("方法值:" + forObject);
            return forObject + "hi-333" + url;
        }
        return "11";
    }



    @GetMapping("/getpm5")
    public String getRoundAndEasyTemplate() {
        String url = "http://provider/gethi";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println("方法值:" + forObject);
        return forObject;

    }

    @GetMapping("/getfe")
    public String getfe() {
        String url = "http://provider/gethi";
//        final String forObject = restTemplate.getForObject(url, String.class);
        final String forObject = restTemplate.getForObject(url, String.class);
        final ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println("内容值:" + forEntity);
        return "getfe";
    }

    @GetMapping("/getMap1")
    public Map<String,String> getMapByObject() {
        String url = "http://provider/getMap";
        Map<String,String> forObject = restTemplate.getForObject(url, Map.class);
        System.out.println("内容值1:" + forObject);
        return forObject;
    }

    @GetMapping("/getMap2")
    public Map<String,String> getMap() {
        String url = "http://provider/getMap";
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url,Map.class);
        Map body = forEntity.getBody();
        System.out.println("内容值2:" +body );
        return body;
    }
    @GetMapping("/getMap3")
    public Person getMap3() {
        String url = "http://provider/getObj1?name={name}";
        Map<String, String> map = Collections.singletonMap("name", "memeda");
        System.out.println("内容值1:" +map );
        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url,Person.class,map);
        Person body = forEntity.getBody();
        System.out.println("内容值2:" +body );
        return body;
    }


    @GetMapping("/getObj")
    public Person getObj() {
        String url = "http://provider/getObj";
        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url, Person.class);
        System.out.println("内容值2:" +forEntity );
        final Person body = forEntity.getBody();
        return body;
    }
    @GetMapping("/getObj1")
    public Person getObj1() {
        String url = "http://provider/getObj1?name={1}";
        Person forEntity = restTemplate.getForObject(url, Person.class,"liyq666");
        System.out.println("内容值2:" +forEntity );
        return forEntity;
    }

    @GetMapping("/getObjParam")
    public Person getObjParam() {
        String url = "http://provider/getObj?name={1}";
        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url, Person.class,"hhh..");
        System.out.println("内容值2:" +forEntity );
        Person body = forEntity.getBody();
        return body;
    }


    @GetMapping("/postParam")
    public Object postParam(HttpServletResponse response) throws IOException {
        String url ="http://provider/postParam";
        Map<String, String> map = Collections.singletonMap("name", "CAP");
        URI uri = restTemplate.postForLocation(url, map, Person.class);
        System.out.println(uri);
        response.sendRedirect(String.valueOf(uri));
        return "123";
    }

}

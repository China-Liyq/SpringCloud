package com.chinaliyq.eurekaconsumer2.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Autowired
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

    @Autowired
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

    @Autowired
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
        }

        return "hi-333";
    }
}

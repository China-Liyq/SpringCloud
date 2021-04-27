package com.chinaliyq.eurekaconsumer2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/22 - 14:10
 * @Version: 1.0
 **/
@RestController
public class RestTemplateController {
    @Value("${server.port}")
    String port;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    @RequestMapping("/gettr")
    public String getProviderMethod3(){
        ServiceInstance instance = loadBalancerClient.choose("provider");
        System.out.println("instances---:" + instance);
        if(null != instance){
            String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/gethi";
            System.out.println(url);
            String forObject = restTemplate.getForObject(url, String.class);
            System.out.println("方法值:" + forObject);
        }

        return "hh-333,prot:" + port;
    }
}

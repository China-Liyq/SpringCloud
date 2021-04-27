package com.chinaliyq.userconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/24 - 18:30
 * @Version: 1.0
 **/
//@FeignClient(name = "user-provider")
//@FeignClient(name = "xxoo",url = "http://localhost:81")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/register")
    public String register();
}

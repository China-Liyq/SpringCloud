package com.chinaliyq.userapi;

import com.chinaliyq.userapi.bean.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: liyq
 * @Description: 资源名称
 * @Date: 2021/4/24 - 22:33
 * @Version: 1.0
 **/
@RequestMapping("/User")
public interface UserAPI {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/register")
    public String register();

    @GetMapping("/getById")
    public String getById(int id);

    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person);
}
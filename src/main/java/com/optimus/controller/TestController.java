package com.optimus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {
    /*@Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/redis")
    public void redis(){
        redisTemplate.opsForValue().set("张三","18");
        System.out.println(redisTemplate.opsForValue().get("张三"));

    }*/

}

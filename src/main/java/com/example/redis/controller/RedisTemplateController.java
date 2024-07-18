package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/redis")
@RestController
public class RedisTemplateController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping
    public Object getValue() {
        var ops = stringRedisTemplate.opsForValue();
        String name = ops.get("name");

//        return "OK";
        return name;
    }

}

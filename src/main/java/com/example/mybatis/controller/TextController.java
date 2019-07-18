package com.example.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/text")
@RestController
public class TextController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public String index(){
        stringRedisTemplate.opsForValue().set("text","方法冠福股份",1000);
        String text =stringRedisTemplate.opsForValue().get("text");
        return text;
    }
}

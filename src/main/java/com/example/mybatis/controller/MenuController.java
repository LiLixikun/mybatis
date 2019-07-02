package com.example.mybatis.controller;

import com.example.mybatis.entity.Menu;
import com.example.mybatis.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuMapper mapper;

    @GetMapping("/list")
    public Menu getMenus(){

        return mapper.selectByPrimaryKey(4);
    }
}

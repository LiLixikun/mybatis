package com.example.mybatis.service.impl;

import com.example.mybatis.entity.Product;
import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findList() {
        PageHelper.startPage(1,10);
        List<Product> products=productMapper.findALL();
        PageInfo<Product> pageInfo=new PageInfo<>(products,10);
        log.info("查询的对象是={}",pageInfo);
    }
}
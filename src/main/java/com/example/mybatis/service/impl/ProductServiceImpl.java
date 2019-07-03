package com.example.mybatis.service.impl;

import com.example.mybatis.entity.Product;
import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products=productMapper.findALL();
        return products;
    }
}

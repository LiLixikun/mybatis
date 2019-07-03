package com.example.mybatis.service;

import com.example.mybatis.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findList(int pageNum,int pageSize);
}

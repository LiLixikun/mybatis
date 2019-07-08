package com.example.mybatis.service.modal;

import com.example.mybatis.entity.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CategoryModel {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    List<Product> productList =new ArrayList<>();
}

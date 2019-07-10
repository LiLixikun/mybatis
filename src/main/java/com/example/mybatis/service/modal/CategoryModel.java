package com.example.mybatis.service.modal;

import com.example.mybatis.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CategoryModel {

    private Integer categoryId;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    List<ProductModel> foods =new ArrayList<>();
}

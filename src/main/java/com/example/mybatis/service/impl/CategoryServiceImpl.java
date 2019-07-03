package com.example.mybatis.service.impl;

import com.example.mybatis.entity.Category;
import com.example.mybatis.mapper.CategoryMapper;
import com.example.mybatis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper mapper;

    @Override
    public Category getCategoryById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> getCategorys() {
        List<Category> categoryList=mapper.selectAll();
        return categoryList;
    }

    @Override
    public void addCategory(Category category) {
        mapper.insertSelective(category);
    }

    @Override
    public void updataCategory(Category category) {
        mapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }
}

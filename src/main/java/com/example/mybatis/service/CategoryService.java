package com.example.mybatis.service;

import com.example.mybatis.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 查询列表
     * @return
     */
    List<Category> getCategorys();

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     * 添加商品类别
     * @param category
     */
    void addCategory(Category category);

    /**
     * 修改商品类别
     * @param category
     */
    void updataCategory(Category category);

    /**
     * 根据id删除
     * @param id
     */
    void deleteCategory(Integer id);
}

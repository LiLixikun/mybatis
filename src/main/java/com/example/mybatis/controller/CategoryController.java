package com.example.mybatis.controller;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.entity.Category;
import com.example.mybatis.service.CategoryService;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public ResultVO getList(){
        return ResultUtil.success(service.getCategorys());
    }

    @GetMapping("/category/{categoryId}")
    public ResultVO getCategory(@PathVariable("categoryId") Integer categoryId){
        service.getCategoryById(categoryId);
        return ResultUtil.success();
    }

    @PostMapping("category")
    public ResultVO addCategory(@RequestBody Category category){
        service.addCategory(category);
        return ResultUtil.success();
    }

    @PutMapping("category")
    public ResultVO updateCategory(@RequestBody Category category){
        service.updataCategory(category);
        return ResultUtil.success();
    }

    @DeleteMapping("/category/{categoryId}")
    public ResultVO DeleteCategory(@PathVariable("categoryId") Integer categoryId){
        service.deleteCategory(categoryId);
        return ResultUtil.success();
    }
}

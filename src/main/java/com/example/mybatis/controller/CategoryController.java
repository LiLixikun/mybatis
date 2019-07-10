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
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResultVO getList(){
        return ResultUtil.success(categoryService.getCategorys());
    }

    @GetMapping("/category/{categoryId}")
    public ResultVO getCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.getCategoryById(categoryId);
        return ResultUtil.success();
    }

    @PostMapping("category")
    public ResultVO addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return ResultUtil.success();
    }

    @PutMapping("category")
    public ResultVO updateCategory(@RequestBody Category category){
        categoryService.updataCategory(category);
        return ResultUtil.success();
    }

    @DeleteMapping("/category/{categoryId}")
    public ResultVO DeleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return ResultUtil.success();
    }
}

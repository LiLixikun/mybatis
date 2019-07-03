package com.example.mybatis.controller;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.service.ProductService;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/list")
    public ResultVO findAll(@RequestParam("pageSize") int pageSize,
                            @RequestParam("pageNum") int pageNum){

        return ResultUtil.success(productService.findList(pageNum,pageSize));
    }
}

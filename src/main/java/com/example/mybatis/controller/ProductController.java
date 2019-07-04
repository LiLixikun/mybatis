package com.example.mybatis.controller;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.entity.Product;
import com.example.mybatis.service.ProductService;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/list")
    public ResultVO findAll(@RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                            @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum){

        return ResultUtil.success(productService.findList(pageNum,pageSize));
    }

    @PostMapping("/product")
    public ResultVO add(@RequestBody Product product){
        productService.addProduct(product);
        return ResultUtil.success();
    }

    @GetMapping("/product/{productId}")
    public ResultVO getBuId(@PathVariable("productId") String id){
        Product product=productService.findById(id);
        return ResultUtil.success(product);
    }

    @PutMapping("/product/{productId}")
    public ResultVO update(@RequestBody Product product,@PathVariable("productId") String productId){
        productService.updateProduct(product,productId);
        return ResultUtil.success();
    }

    @DeleteMapping("/product/{productId}")
    public ResultVO delete(@PathVariable("productId") String productId){
        productService.deleteProduct(productId);
        return ResultUtil.success();
    }
}

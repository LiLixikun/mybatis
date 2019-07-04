package com.example.mybatis.service;

import com.example.mybatis.DTO.CartDTO;
import com.example.mybatis.entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {

    /**
     * 根据分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Product> findList(int pageNum, int pageSize);

    /**
     * 返回前端APP联动数据
     * @return
     */
    List<Product> getTreeProdct();

    /**
     * 查询详情
     * @param id
     * @return
     */
    Product findById(String id);

    /**
     * 添加商品
     * @param product
     */
    void addProduct(Product product);

    /**
     * 修改商品信息
     * @param product
     */
    void updateProduct(Product product,String productId);

    /**
     * 删除商品
     * @param id
     */
    void deleteProduct(String id);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);


    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}

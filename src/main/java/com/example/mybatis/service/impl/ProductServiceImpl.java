package com.example.mybatis.service.impl;

import com.example.mybatis.DTO.CartDTO;
import com.example.mybatis.entity.Product;
import com.example.mybatis.enums.ResultEnum;
import com.example.mybatis.exceptionHandle.SellException;
import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.service.ProductService;
import com.example.mybatis.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> findList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.findALL();
        PageInfo<Product> pageInfo = new PageInfo<>(products, pageSize);
        return pageInfo;
    }

    @Override
    public List<Product> getTreeProdct() {
        return null;
    }

    @Override
    @Transactional
    public Product findById(String id) {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new SellException(ResultEnum.PRODUCT_NO_EXIS);
        }
        return product;
    }

    @Override
    public void addProduct(Product product) {
        product.setProductId(KeyUtil.getProductId());
        productMapper.insertSelective(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product, String productId) throws SellException {
        findById(productId);
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        findById(id);
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void
    increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) throws SellException{
        for (CartDTO cartDTO:cartDTOList){
            Product product=productMapper.selectByPrimaryKey(cartDTO.getProductId());
            if(product==null){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIS);
            }
            Integer productStock=product.getProductStock()-cartDTO.getProductStock();
            //潘哥库存是否足够
            if(productStock<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERR);
            }
            product.setProductStock(productStock);
            productMapper.updateByPrimaryKeySelective(product);
        }
    }
}

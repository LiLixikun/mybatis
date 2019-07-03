package com.example.mybatis.service.impl;

import com.example.mybatis.entity.Menu;
import com.example.mybatis.mapper.MenuMapper;
import com.example.mybatis.service.MenuService;
import com.example.mybatis.service.modal.MenuModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<MenuModal> selectTree() {
        return menuMapper.selectTree(0);
    }

    @Override
    public void addMenu(MenuModal menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void updateMenu(MenuModal menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void deleteById(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}

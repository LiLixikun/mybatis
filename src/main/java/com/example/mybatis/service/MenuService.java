package com.example.mybatis.service;

import com.example.mybatis.entity.Menu;
import com.example.mybatis.service.modal.MenuModal;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> selectAll();

    /**
     * 查询菜单树
     * @return
     */
    List<MenuModal> selectTree();

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    void addMenu(MenuModal menu);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    void updateMenu(MenuModal menu);

    /**
     * 根据id删除菜单
     * @param id
     */
    void deleteById(Integer id);
}

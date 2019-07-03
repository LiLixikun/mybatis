package com.example.mybatis.mapper;

import com.example.mybatis.entity.Menu;
import com.example.mybatis.service.modal.MenuModal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> selectAll();

    List<MenuModal> selectTree(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(MenuModal record);

    MenuModal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuModal record);

    int updateByPrimaryKey(MenuModal record);

}
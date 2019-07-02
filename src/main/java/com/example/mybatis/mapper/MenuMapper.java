package com.example.mybatis.mapper;

import com.example.mybatis.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}
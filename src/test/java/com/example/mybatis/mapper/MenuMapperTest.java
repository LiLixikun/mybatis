package com.example.mybatis.mapper;

import com.example.mybatis.entity.Menu;
import com.example.mybatis.my.Dog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuMapperTest {

    @Resource
    private MenuMapper mapper;

    @Test
    public void selectAll() {
        List<Menu> menuList=mapper.selectAll();
        Assert.assertNotNull(menuList);
    }

    @Test
    public void selectTree() {
//        Dog dog = new Dog();
//        dog.name="小狗";
//        dog.age=10;
//        dog.eat();
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}
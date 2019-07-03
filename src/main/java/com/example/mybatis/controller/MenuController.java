package com.example.mybatis.controller;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.entity.Menu;
import com.example.mybatis.mapper.MenuMapper;
import com.example.mybatis.service.MenuService;
import com.example.mybatis.service.modal.MenuModal;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResultVO getMenus() {
        List<MenuModal> menuList = menuService.selectTree();
        return ResultUtil.success(menuList);
    }

    @PostMapping("/save")
    public ResultVO addMenu(@RequestBody MenuModal menu) {
        menuService.addMenu(menu);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    public ResultVO updateMenu(@RequestBody MenuModal menu) {
        menuService.updateMenu(menu);
        return ResultUtil.success();
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id) {
        menuService.deleteById(id);
        return ResultUtil.success();
    }
}

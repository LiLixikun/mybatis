package com.example.mybatis.controller;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.entity.UserInfo;
import com.example.mybatis.service.UserService;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResultVO getUsers() {
        return ResultUtil.success(userService.getUsers());
    }

    @GetMapping("/users/{openId}")
    public ResultVO SelectOne(@PathVariable("openId") String openId) {
        return ResultUtil.success(userService.getUserById(openId));
    }

    @PostMapping("/users")
    public ResultVO addUser(UserInfo userInfo) {
        return ResultUtil.success(userService.addUser(userInfo));
    }

    @PutMapping("/users/{openId}")
    public ResultVO updateUser(@PathVariable("openId") String openId, UserInfo userInfo) {
        return ResultUtil.success(userService.updateUserById(openId, userInfo));
    }

    @DeleteMapping("/users/{openId}")
    public ResultVO delUser(@PathVariable("openId") String openId) {
        userService.delUserById(openId);
        return ResultUtil.success();
    }
}

package com.example.mybatis.service.impl;

import com.example.mybatis.entity.UserInfo;
import com.example.mybatis.mapper.UserInfoMapper;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper mapper;

    @Override
    public List<UserInfo> getUsers() {
        return mapper.getUsers();
    }

    @Override
    public UserInfo getUserById(String openId) {
        UserInfo userInfo = mapper.selectByPrimaryKey(openId);
        return userInfo;
    }

    @Override
    public void addUser(UserInfo userInfo) {
        mapper.insertSelective(userInfo);
    }

    @Override
    public void updateUserById(String openId, UserInfo userInfo) {
        mapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public void delUserById(String openId) {
        mapper.deleteByPrimaryKey(openId);
    }
}

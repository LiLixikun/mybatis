package com.example.mybatis.service.impl;

import com.example.mybatis.service.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MainImpl implements Main {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail() {

    }
}

package com.example.mybatis.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MainImplTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception{
        SimpleMailMessage message=new SimpleMailMessage();

        try {
            //发件人
            message.setFrom("15068610616@163.com");
            //收件人
            message.setTo("460373604@qq.com");
            //标题
            message.setSubject("subject");
            //文本
            message.setText("message text");
            //附件
            //helper.addAttachment("downFile",new File("D:\\cygwin64\\home\\workspace3\\learn-demo\\zookeeper\\src\\test\\java\\com\\tzxylao\\design\\ZookeeperApplicationTests.java"));
            mailSender.send(message);
        }catch (Exception e){

        }
    }
}
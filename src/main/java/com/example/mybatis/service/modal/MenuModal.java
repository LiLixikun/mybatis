package com.example.mybatis.service.modal;

import com.example.mybatis.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuModal {
    private Integer id;

    private String menuName;

    private String menuIcon;

    private String link;

    private Integer pid;

    private List<Menu> children;
}

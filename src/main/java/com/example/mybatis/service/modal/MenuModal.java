package com.example.mybatis.service.modal;

import com.example.mybatis.entity.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuModal implements Serializable {

    private static final long serialVersionUID = -1191740689393811980L;

    private Integer id;

    private String menuName;

    private String menuIcon;

    private String link;

    private Integer pid;

    private List<Menu> children;
}

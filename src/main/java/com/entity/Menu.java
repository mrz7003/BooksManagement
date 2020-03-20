package com.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 生成menu表实体类
 */
@Data
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;// bigint primary key AUTO_INCREMENT comment '菜单编号',
    private Integer parentId;// int comment '上级菜单',
    private String menuName;// varchar(50) comment '菜单名称',
    private String menuUrl;// varchar(100) comment '菜单URL'
    @Transient
    private List<Menu> secondaryMenu;
}

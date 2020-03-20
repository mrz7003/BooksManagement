package com.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 生成user_menu表实体类
 */
@Data
@Table(name = "user_menu")
public class UserMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userMenuId;// bigint primary key AUTO_INCREMENT comment '用户菜单编号',
    private Boolean userAuthority;// bigint comment '用户权限',
    private Long menuId;// bigint comment '菜单编号'
}

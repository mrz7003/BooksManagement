package com.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 生成books_category表的实体类
 */
@Data
@Table(name = "books_category")
public class BooksCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;// bigint primary key AUTO_INCREMENT comment '类别编号',
    private String categoryName;// varchar(50) comment '类别名称'
}

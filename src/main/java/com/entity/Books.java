package com.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 生成books表的实体类
 */
@Data
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;// bigint primary key AUTO_INCREMENT comment '书籍编号',
    private Long categoryId;// bigint comment '类别编号',
    @Transient
    private String categoryName;// varchar(50) comment '类别名称'
    private String bookName;// varchar(50) comment '书籍名称',
    private String bookAuthor;// varchar(50) comment '作者',
    private Long bookGross;// bigint comment '总册数',
    private Long bookInventory;// bigint comment '库存',
    private String publishingHouse;//  varchar(100) comment '出版社',
    private String publicationDate;//  timestamp comment '出版日期',
    private Boolean bookSign;// tinyint comment '是否下架' 0代表下架 1代表上架
}

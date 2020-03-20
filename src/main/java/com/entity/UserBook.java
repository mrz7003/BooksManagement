package com.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 生成user_book表的实体类
 */
@Data
@Table(name = "user_book")
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;// bigint primary key AUTO_INCREMENT comment '借阅编号',
    private Long userId;// bigint comment '用户编号',
    private Long bookId;// bigint comment '图书编号',
    @Transient
    private Books books;
    private Timestamp borrowDate;// timestamp comment '借阅时间'
}

package com.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 生成user_visit表的实体类
 */
@Data
@Table(name = "user_visit")
public class UserVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitId;// bigint primary key AUTO_INCREMENT comment '访问编号',
    private Timestamp visitDate;// timestamp comment '访问日期',
    private Integer visitPeople;// int comment '访问人数',
    private Integer visitBorrow;// int comment '借阅数'
}

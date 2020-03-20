package com.utils;

import lombok.Data;

/**
 * 前台数据用户访问图表展示实体类
 */
@Data
public class BrokenLineData {
    private String visitDate;// timestamp comment '访问日期',
    private Integer visitPeople;// int comment '访问人数',
    private Integer visitBorrow;// int comment '借阅数'
}

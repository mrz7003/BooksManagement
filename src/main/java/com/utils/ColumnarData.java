package com.utils;

import lombok.Data;

/**
 * 前台数据柱形图表展示实体类
 */
@Data
public class ColumnarData {
    private String category;
    private Long gross;
    private Long inventory;
}

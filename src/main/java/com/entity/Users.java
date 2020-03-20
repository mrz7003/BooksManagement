package com.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 生成users表实体类
 */
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;// bigint primary key AUTO_INCREMENT comment '用户编号',
    private String userName;// varchar(100) comment '用户名',
    private String userCode;// varchar(100) comment '用户密码',
    private String userPhone;// varchar(11) comment '手机号码',
    private Boolean userAuthority;// tinyint comment '权限 0代表用户 1代表管理员',
    private Boolean userSign;// tinyint comment '状态 0代表禁用 1代表正常',
    private Timestamp userLoginTime;// timestamp comment '最近一次登录时间',
    private Timestamp createTime;// timestamp comment '创建时间'
}

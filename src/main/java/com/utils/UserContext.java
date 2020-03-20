package com.utils;

import lombok.Data;

/**
 * 用户登录信息的存储
 */
@Data
public class UserContext {
    private Long userId;
    private String userName;
    private String userCode;
    private Boolean userAuthority;
}

package com.service;

import com.entity.Users;
import com.github.pagehelper.PageInfo;
import com.utils.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UsersService {

    PageInfo<Users> usersQueryAll(Integer pageNum, Integer pageSize, String searchUser);

    Users usersQueryOne(Long userId);

    int usersEditSign(Users users);

    String usersLogin(Users user, Boolean checked, HttpServletResponse response, HttpServletRequest request);

    String verify(String token, HttpServletRequest request);

    UserContext lookUserContext(HttpServletRequest request);

    String logout(HttpServletResponse response, HttpServletRequest request);

    String sendCode(String userPhone);

    String userRegister(Users user, String verificationCode);

    Users checkName(String userName);

    String checkVerificationCode(String userPhone, String verificationCode);

    int changeCode(Users user);
}

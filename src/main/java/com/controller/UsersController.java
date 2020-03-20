package com.controller;

import com.config.ReturnVariable;
import com.entity.Users;
import com.github.pagehelper.PageInfo;
import com.service.UsersService;
import com.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接收Users表的请求并处理
 */
@RestController
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * Users表的分页、模糊查询 转发给业务层
     *
     * @param pageNum
     * @param pageSize
     * @param searchUser
     * @return
     */
    @GetMapping("/UsersQuery")
    public PageInfo<Users> usersQueryAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String searchUser) {
        PageInfo<Users> pageInfo = usersService.usersQueryAll(pageNum, pageSize, searchUser);
        if (pageInfo != null && pageInfo.getSize() > 0) {
            return pageInfo;
        }
        return null;
    }

    /**
     * Users表查询一个用户信息请求 转发给业务层
     *
     * @param userId
     * @return
     */
    @GetMapping("/UsersQueryOne")
    public Users usersQueryOne(@RequestParam Long userId) {
        Users user = usersService.usersQueryOne(userId);
        if (user != null) {
            return user;
        }
        return null;
    }

    /**
     * Users表修改封禁正常标记 转发给业务层
     *
     * @param users
     * @return
     */
    @PutMapping("/UsersEditSign")
    public String usersEditSign(Users users) {
        int i = usersService.usersEditSign(users);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * Users表用户登录请求 转发给业务层
     *
     * @param user
     * @param checked
     * @return
     */
    @PostMapping("/UsersLogin")
    public String usersLogin(Users user, @RequestParam Boolean checked, HttpServletResponse response, HttpServletRequest request) {
        return usersService.usersLogin(user, checked, response, request);
    }

    /**
     * 查看用户登录状态 转发给业务层
     *
     * @param token
     * @return
     */
    @GetMapping("/Verify")
    public String verify(@CookieValue(value = "token", required = false) String token, HttpServletRequest request) {
        return usersService.verify(token, request);
    }


    /**
     * 进入主页面 获取登录用户的姓名 跟 ID 、权限转发给业务层
     *
     * @param request
     * @return
     */
    @GetMapping("/LookUserContext")
    public UserContext lookUserContext(HttpServletRequest request) {
        return usersService.lookUserContext(request);
    }

    /**
     * 用户退出请求 转发给业务层
     *
     * @param response
     * @return
     */
    @GetMapping("/Logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        return usersService.logout(response, request);
    }

    /**
     * 用户注册发送验证码 转发给业务层
     *
     * @param userPhone
     * @return
     */
    @PostMapping("/SendCode")
    public String sendCode(@RequestParam String userPhone) {
        return usersService.sendCode(userPhone);
    }

    /**
     * 用户注册请求 转发给业务层
     *
     * @param user
     * @param verificationCode
     * @return
     */
    @PostMapping("/UserRegister")
    public String userRegister(Users user, @RequestParam String verificationCode) {
        return usersService.userRegister(user, verificationCode);
    }

    /**
     * 忘记密码 验证用户是否存在 转发给业务层
     *
     * @param userName
     * @return
     */
    @GetMapping("/CheckName")
    public Users checkName(@RequestParam String userName) {
        Users user = usersService.checkName(userName);
        if (user != null) {
            return user;
        }
        return null;
    }

    /**
     * 忘记密码 验证手机验证码 转发给业务层
     *
     * @param userPhone
     * @param verificationCode
     * @return
     */
    @GetMapping("CheckVerificationCode")
    public String checkVerificationCode(@RequestParam String userPhone, @RequestParam String verificationCode) {
        return usersService.checkVerificationCode(userPhone, verificationCode);
    }

    /**
     * 忘记密码 验证通过 修改密码 转发给业务层
     *
     * @param user
     * @return
     */
    @PutMapping("/ChangeCode")
    public String changeCode(Users user) {
        int i = usersService.changeCode(user);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }
}

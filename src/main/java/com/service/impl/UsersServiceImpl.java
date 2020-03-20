package com.service.impl;

import com.config.ReturnVariable;
import com.entity.UserVisit;
import com.entity.Users;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserVisitMapper;
import com.mapper.UsersMapper;
import com.service.UsersService;
import com.utils.JwtUtils;
import com.utils.NumberUtils;
import com.utils.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired(required = false)
    private UsersMapper usersMapper;

    @Autowired(required = false)
    private UserVisitMapper userVisitMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 处理用户信息分页、模糊查询请求 访问数据库 并返回查询结果
     *
     * @param pageNum
     * @param pageSize
     * @param searchUser
     * @return
     */
    @Override
    public PageInfo<Users> usersQueryAll(Integer pageNum, Integer pageSize, String searchUser) {
        //如果搜索框关键字部位空则模糊查询
        Example example = new Example(Users.class);
        if (StringUtils.isNotBlank(searchUser)) {
            example.createCriteria().andLike("userName", "%" + searchUser + "%");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Users> users = usersMapper.selectByExample(example);
        return new PageInfo<>(users);
    }

    /**
     * 处理查询一个用户信息的请求 访问数据库 并返回查询结果
     *
     * @param userId
     * @return
     */
    @Override
    public Users usersQueryOne(Long userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

    /**
     * 处理修改用户封禁/解封状态请求 访问数据库 并返回修改结果
     *
     * @param users
     * @return
     */
    @Transactional
    @Override
    public int usersEditSign(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    /**
     * 处理用户登录请求 访问数据库 并返回查询结果
     * 判断用户是否勾选保存密码 并操作cookie redis session
     *
     * @param user
     * @param checked
     * @return
     */
    @Transactional
    @Override
    public String usersLogin(Users user, Boolean checked, HttpServletResponse response, HttpServletRequest request) {
        Users selectUser = usersMapper.selectUserByUserName(user.getUserName());
        //判断用户是否存在
        if (selectUser != null) {
            //判断密码是否正确
            if (DigestUtils.md5DigestAsHex(user.getUserCode().getBytes()).equals(selectUser.getUserCode())) {
                //判断用户账号使用状态
                if (selectUser.getUserSign()) {
                    // 普通用户登录 记录访问记录表
                    if (selectUser.getUserAuthority() == false) {
                        UserVisit userVisit = userVisitMapper.selectUserVisitLastOne();
                        userVisit.setVisitPeople(userVisit.getVisitPeople() + 1);
                        userVisitMapper.updateByPrimaryKey(userVisit);
                    }
                    //记录成功登录的用户信息 暂时存入session
                    UserContext userContext = new UserContext();
                    userContext.setUserId(selectUser.getUserId());
                    userContext.setUserName(selectUser.getUserName());
                    userContext.setUserCode(selectUser.getUserCode());
                    userContext.setUserAuthority(selectUser.getUserAuthority());
                    HttpSession session = request.getSession();
                    session.setAttribute("userContext", userContext);
                    //是否记住密码
                    if (checked) {
                        //生成token 并存入Redis Cookie
                        String token = JwtUtils.generateJsonWebToken(userContext);
                        //有效期7天
                        stringRedisTemplate.opsForValue().set("UserLogin_token", token, 7, TimeUnit.DAYS);
                        Cookie cookie = new Cookie("token", token);
                        cookie.setPath("/");
                        cookie.setMaxAge(1000 * 60 * 60 * 24 * 7);
                        cookie.setHttpOnly(true);
                        response.addCookie(cookie);
                    }
                    //修改用户最后一次登录时间
                    Users updateUser = new Users();
                    updateUser.setUserId(selectUser.getUserId());
                    updateUser.setUserLoginTime(new Timestamp(new Date().getTime()));
                    usersMapper.updateByPrimaryKeySelective(updateUser);
                    return ReturnVariable.RETURN_ONE;
                }
                return ReturnVariable.RETURN_FOUR;
            }
            return ReturnVariable.RETURN_THREE;
        }
        return ReturnVariable.RETURN_TWO;
    }

    /**
     * 检查是否登录状态 先查看有没有session 再比较Cookie携带的token 跟Redis中的token是否一致 如果一致登录 并解析token 把用户信息存入session
     *
     * @param token
     * @param request
     * @return
     */
    @Override
    public String verify(String token, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        if (userContext != null) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        try {
            String redis_token = stringRedisTemplate.opsForValue().get("UserLogin_token");
            if (token.equals(redis_token)) {
                UserContext userInfo = JwtUtils.analysisJWTToUserContext(redis_token);
                session.setAttribute("userContext", userInfo);
                return ReturnVariable.RETURN_SUCCESS;
            }
        } catch (Exception e) {
            return ReturnVariable.RETURN_ERROR;
        }
        return ReturnVariable.RETURN_ERROR;
    }


    /**
     * 检查登录时如果浏览器保存了用户信息 从中解析用户名 并返回给主页显示
     *
     * @param request
     * @return
     */
    @Override
    public UserContext lookUserContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        //抹去密码
        userContext.setUserCode(null);
        return userContext;
    }

    /**
     * 用户退出 删除Coolie 跟 Redis 中的token 并删除session中的登录用户信息上下文
     *
     * @param response
     * @return
     */
    @Override
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        stringRedisTemplate.delete("UserLogin_token");
        HttpSession session = request.getSession();
        session.removeAttribute("userContext");
        return ReturnVariable.RETURN_SUCCESS;
    }

    /**
     * 生成6位验证码 存入Redis 向用户手机发送请求 略 返回处理结果
     *
     * @param userPhone
     * @return
     */
    @Override
    public String sendCode(String userPhone) {
        try {
            String verificationCode = NumberUtils.generateCode(6);
            // 有效期5分钟
            stringRedisTemplate.opsForValue().set("verificationCode:" + userPhone, verificationCode, 5, TimeUnit.MINUTES);
            //发送短信 略
            return ReturnVariable.RETURN_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * 处理用户注册请求 访问数据库 并返回添加结果
     *
     * @param user
     * @param verificationCode
     * @return
     */
    @Transactional
    @Override
    public String userRegister(Users user, String verificationCode) {
        // 查看用户名是否存在
        Users users = usersMapper.selectUserByUserName(user.getUserName());
        if (users == null) {
            // 查看redis 是否有该用户手机号的验证码
            String redis_verificationCode = stringRedisTemplate.opsForValue().get("verificationCode:" + user.getUserPhone());
            System.out.println(redis_verificationCode);
            if (StringUtils.isNotBlank(redis_verificationCode)) {
                // 判断验证码是否正确
                if (redis_verificationCode.equals(verificationCode)) {
                    user.setUserCode(DigestUtils.md5DigestAsHex(user.getUserCode().getBytes()));
                    // 判断是否注册成功
                    int i = usersMapper.insert(user);
                    if (i == 1) {
                        // 注册成功删除redis 中的验证码
                        stringRedisTemplate.delete("verificationCode:" + user.getUserPhone());
                        return ReturnVariable.RETURN_ONE;
                    }
                    return ReturnVariable.RETURN_TWO;
                }
                return ReturnVariable.RETURN_FOUR;
            }
            return ReturnVariable.RETURN_FIVE;
        }
        return ReturnVariable.RETURN_THREE;
    }

    /**
     * 根据用户名查看用户是否存在 访问数据库 返回查询结果
     *
     * @param userName
     * @return
     */
    @Override
    public Users checkName(String userName) {
        Users user = usersMapper.selectUserByUserName(userName);
        // 抹去关键信息
        if (user != null) {
            user.setUserCode(null);
            user.setUserSign(null);
            user.setUserLoginTime(null);
            user.setUserAuthority(null);
            user.setCreateTime(null);
        }
        return user;
    }

    /**
     * 接收用户发送的手机号跟验证码 跟redis中的验证码比较 并返回验证结果
     *
     * @param userPhone
     * @param verificationCode
     * @return
     */
    @Override
    public String checkVerificationCode(String userPhone, String verificationCode) {
        String redis_verificationCode = stringRedisTemplate.opsForValue().get("verificationCode:" + userPhone);
        // 根据手机号查询redis中的验证码是否存在
        if (StringUtils.isNotBlank(redis_verificationCode)) {
            // 比较验证码是否正确
            if (redis_verificationCode.equals(verificationCode)) {
                // 删除redis中的验证码
                stringRedisTemplate.delete("verificationCode:" + userPhone);
                return ReturnVariable.RETURN_ONE;
            }
            return ReturnVariable.RETURN_TWO;
        }
        return ReturnVariable.RETURN_THREE;
    }

    /**
     * 接收用户ID和新密码 访问数据库 返回修改结果
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public int changeCode(Users user) {
        user.setUserCode(DigestUtils.md5DigestAsHex(user.getUserCode().getBytes()));
        return usersMapper.updateByPrimaryKeySelective(user);
    }

}

package com.mapper;

import com.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


/**
 * Mapper接口 继承tk:mybatis 超类
 */
public interface UsersMapper extends Mapper<Users> {

    @Select("select * from users where user_name = #{userName}")
    Users selectUserByUserName(@Param("userName") String userName);
}

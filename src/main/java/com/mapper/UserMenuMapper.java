package com.mapper;

import com.entity.UserMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Mapper接口 继承tk:mybatis 超类
 */
public interface UserMenuMapper extends Mapper<UserMenu> {

    @Select("select * from user_menu where user_authority = #{userAuthority}")
    List<UserMenu> selectMenuIdByUserAuthority(@Param("userAuthority") Boolean userAuthority);
}

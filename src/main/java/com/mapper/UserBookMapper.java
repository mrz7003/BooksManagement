package com.mapper;

import com.entity.UserBook;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Mapper接口 继承tk:mybatis 超类
 */
public interface UserBookMapper extends Mapper<UserBook> {

    @Select("select book_id from user_book where user_id = #{userId}")
    List<Long> selectBookIdByUserId(@Param("userId") Long userId);
}

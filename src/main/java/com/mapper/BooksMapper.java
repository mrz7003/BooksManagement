package com.mapper;

import com.entity.Books;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * Mapper接口 继承tk:mybatis 超类
 */
public interface BooksMapper extends Mapper<Books> {

    @Select("select count(*) from books where category_id = #{categoryId}")
    int selectUsersByCategoryId(@Param("categoryId") Long categoryId);

    @Select("select * from books where category_id = #{categoryId} and book_sign = 1")
    List<Books> selectBookGrossByCategoryId(@Param("categoryId") Long categoryId);
}

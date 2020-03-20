package com.mapper;

import com.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Mapper接口 继承tk:mybatis 超类
 */
public interface MenuMapper extends Mapper<Menu> {

    @Select("select * from menu where parent_id = #{menuId}")
    List<Menu> selectMenuByParentId(@Param("menuId") Long menuId);
}

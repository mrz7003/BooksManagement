package com.mapper;

import com.entity.UserVisit;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Mapper接口 继承tk:mybatis 超类
 */
public interface UserVisitMapper extends Mapper<UserVisit> {

    @Select("select * from user_visit order by visit_id desc limit 0,7")
    List<UserVisit> selectUserVisitLastSeven();

    @Select("select * from user_visit order by visit_id desc limit 0,1")
    UserVisit selectUserVisitLastOne();
}

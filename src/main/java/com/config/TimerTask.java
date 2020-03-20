package com.config;

import com.entity.UserVisit;
import com.mapper.UserVisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class TimerTask {

    @Autowired(required = false)
    private UserVisitMapper userVisitMapper;

    /**
     * 每天凌晨自动添加在用户访问信息表 自动添加一条今日的空数据访问量
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void insertUserVisit() {
        UserVisit userVisit = new UserVisit();
        userVisit.setVisitDate(new Timestamp(new Date().getTime()));
        userVisit.setVisitPeople(0);
        userVisit.setVisitBorrow(0);
        userVisitMapper.insert(userVisit);
    }
}

package com.controller;

import com.entity.UserVisit;
import com.utils.BrokenLineData;
import com.utils.CircularData;
import com.service.ChartsService;
import com.utils.ColumnarData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 接收图表查询请求
 */
@RestController
@RequestMapping("/Charts")
public class ChartsController {

    @Autowired
    private ChartsService chartsService;

    /**
     * 接收查询圆形图表数据请求 转发给业务层
     *
     * @return
     */
    @GetMapping("/BooksCharts/ClassifyGross")
    public List<CircularData> classifyGross() {
        return chartsService.classifyGross();
    }

    /**
     * 接收查询柱形图表数据亲求 转发给业务层
     *
     * @return
     */
    @GetMapping("/BooksCharts/ClassifyGrossAndBorrow")
    public List<ColumnarData> classifyGrossAndBorrow() {
        return chartsService.ClassifyGrossAndBorrow();
    }

    /**
     * 接收查询用户访问信息图表数据亲求 转发给业务层
     *
     * @return
     */
    @GetMapping("/UsersCharts/UserVisit")
    public List<BrokenLineData> userVisitLastSeven() {
        System.out.println(chartsService.userVisitLastSeven());
        return chartsService.userVisitLastSeven();
    }
}

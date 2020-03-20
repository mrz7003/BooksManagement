package com.service.impl;

import com.entity.Books;
import com.entity.BooksCategory;
import com.entity.UserVisit;
import com.mapper.BooksCategoryMapper;
import com.mapper.BooksMapper;
import com.mapper.UserVisitMapper;
import com.service.ChartsService;
import com.utils.BrokenLineData;
import com.utils.CircularData;
import com.utils.ColumnarData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartsServiceImpl implements ChartsService {

    @Autowired(required = false)
    private BooksCategoryMapper booksCategoryMapper;

    @Autowired(required = false)
    private BooksMapper booksMapper;

    @Autowired(required = false)
    private UserVisitMapper userVisitMapper;

    /**
     * 查询图书类别 跟每个类别下的图书总册数 访问数据库 返回查询结果
     *
     * @return
     */
    @Override
    public List<CircularData> classifyGross() {
        List<CircularData> circularDataList = new ArrayList<>();
        List<BooksCategory> booksCategories = booksCategoryMapper.selectAll();
        for (BooksCategory booksCategory : booksCategories) {
            CircularData circularData = new CircularData();
            Long gross = new Long(0);
            List<Books> booksList = booksMapper.selectBookGrossByCategoryId(booksCategory.getCategoryId());
            for (Books book : booksList) {
                gross = gross + book.getBookGross();
            }
            circularData.setCategory(booksCategory.getCategoryName());
            circularData.setGross(gross);
            circularDataList.add(circularData);
        }
        return circularDataList;
    }

    /**
     * 查询图书类别 跟每个类别下的图书总册数跟库存 访问数据库 返回查询结果
     *
     * @return
     */
    @Override
    public List<ColumnarData> ClassifyGrossAndBorrow() {
        List<ColumnarData> columnarDataList = new ArrayList<>();
        List<BooksCategory> booksCategories = booksCategoryMapper.selectAll();
        for (BooksCategory booksCategory : booksCategories) {
            ColumnarData columnarData = new ColumnarData();
            Long gross = new Long(0);
            Long inventory = new Long(0);
            List<Books> booksList = booksMapper.selectBookGrossByCategoryId(booksCategory.getCategoryId());
            for (Books book : booksList) {
                gross = gross + book.getBookGross();
                inventory = inventory + book.getBookInventory();
            }
            columnarData.setCategory(booksCategory.getCategoryName());
            columnarData.setGross(gross);
            columnarData.setInventory(inventory);
            columnarDataList.add(columnarData);
        }
        return columnarDataList;
    }

    /**
     * 查询用户访问信息表 查询出后7条数据 访问数据库 返回查询结果
     *
     * @return
     */
    @Override
    public List<BrokenLineData> userVisitLastSeven() {
        List<BrokenLineData> brokenLineDataList = new ArrayList<>();
        List<UserVisit> userVisits = userVisitMapper.selectUserVisitLastSeven();
        for (int i = 6; i >= 0; i--) {
            BrokenLineData brokenLineData = new BrokenLineData();
            brokenLineData.setVisitDate(new SimpleDateFormat("MM/dd").format(userVisits.get(i).getVisitDate()));
            brokenLineData.setVisitPeople(userVisits.get(i).getVisitPeople());
            brokenLineData.setVisitBorrow(userVisits.get(i).getVisitBorrow());
            brokenLineDataList.add(brokenLineData);
        }
        return brokenLineDataList;
    }
}

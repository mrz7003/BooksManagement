package com.service.impl;

import com.config.ReturnVariable;
import com.entity.BooksCategory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.BooksCategoryMapper;
import com.mapper.BooksMapper;
import com.service.BooksCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BooksCategoryServiceImpl implements BooksCategoryService {

    @Autowired(required = false)
    private BooksMapper booksMapper;

    @Autowired(required = false)
    private BooksCategoryMapper booksCategoryMapper;

    /**
     * 处理书籍类别信息分页、模糊查询请求 访问数据库 并返回查询结果
     *
     * @param pageNum
     * @param pageSize
     * @param searchCategory
     * @return
     */
    @Override
    public PageInfo<BooksCategory> categoryQueryAll(Integer pageNum, Integer pageSize, String searchCategory) {
        //如果搜索框关键字部位空则模糊查询
        Example example = new Example(BooksCategory.class);
        if (StringUtils.isNotBlank(searchCategory)) {
            example.createCriteria().andLike("categoryName", "%" + searchCategory + "%");
        }
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<BooksCategory> booksCategories = booksCategoryMapper.selectByExample(example);
        return new PageInfo<>(booksCategories);
    }

    /**
     * 处理书籍类别添加请求 访问数据库 并返回添加结果
     *
     * @param bookCategory
     * @return
     */
    @Transactional
    @Override
    public String categoryInsert(BooksCategory bookCategory) {
        //查看数据库是否存在此条数据
        BooksCategory booksCategory = booksCategoryMapper.selectOne(bookCategory);
        if (booksCategory == null) {
            int insert = booksCategoryMapper.insert(bookCategory);
            // 添加成功
            if (insert == 1) {
                return ReturnVariable.RETURN_ONE;
            }
            return ReturnVariable.RETURN_THREE;
        }
        return ReturnVariable.RETURN_TWO;
    }

    /**
     * 处理查询一个书籍类别信息的请求 访问数据库 并返回查询结果
     *
     * @param categoryId
     * @return
     */
    @Override
    public BooksCategory categoryQueryOne(Long categoryId) {
        return booksCategoryMapper.selectByPrimaryKey(categoryId);
    }

    /**
     * 处理修改书籍类别信息的请求 访问数据库 并返回修改结果
     *
     * @param bookCategory
     * @return
     */
    @Transactional
    @Override
    public int categoryUpdate(BooksCategory bookCategory) {
        return booksCategoryMapper.updateByPrimaryKey(bookCategory);
    }

    /**
     * 处理删除书籍类别信息的请求 访问数据库 并返回删除结果
     *
     * @param categoryId
     * @return
     */
    @Override
    public String categoryDelete(Long categoryId) {
        int count = booksMapper.selectUsersByCategoryId(categoryId);
        //如果该类别下没书籍就删除
        if (count == 0) {
            int i = booksCategoryMapper.deleteByPrimaryKey(categoryId);
            if (i == 1) {
                return ReturnVariable.RETURN_ONE;
            }
            return ReturnVariable.RETURN_THREE;
        }
        return ReturnVariable.RETURN_TWO;
    }
}

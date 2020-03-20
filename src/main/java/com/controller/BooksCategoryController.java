package com.controller;

import com.config.ReturnVariable;
import com.entity.BooksCategory;
import com.github.pagehelper.PageInfo;
import com.service.BooksCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 接收BooksCategory表的请求并处理
 */
@RestController
@RequestMapping("/Category")
public class BooksCategoryController {

    @Autowired
    private BooksCategoryService booksCategoryService;

    /**
     * BooksCategory表的分页、模糊查询 转发给业务层
     *
     * @param pageNum
     * @param pageSize
     * @param searchCategory
     * @return
     */
    @GetMapping("/CategoryQuery")
    public PageInfo<BooksCategory> categoryQueryAll(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String searchCategory) {
        PageInfo<BooksCategory> pageInfo = booksCategoryService.categoryQueryAll(pageNum, pageSize, searchCategory);
        if (pageInfo != null && pageInfo.getSize() > 0) {
            return pageInfo;
        }
        return null;
    }

    /**
     * BooksCategory表添加数据 转发给业务层
     *
     * @param bookCategory
     * @return
     */
    @PostMapping("/CategoryInsert")
    public String categoryInsert(BooksCategory bookCategory) {
        return booksCategoryService.categoryInsert(bookCategory);
    }

    /**
     * BooksCategory表查询一个书籍类别信息 转发给业务层
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/CategoryQueryOne")
    public BooksCategory categoryQueryOne(@RequestParam Long categoryId) {
        BooksCategory booksCategory = booksCategoryService.categoryQueryOne(categoryId);
        if (booksCategory != null) {
            return booksCategory;
        }
        return null;
    }

    /**
     * BooksCategory表修改数据 转发给业务层
     *
     * @param bookCategory
     * @return
     */
    @PutMapping("/CategoryUpdate")
    public String categoryUpdate(BooksCategory bookCategory) {
        int i = booksCategoryService.categoryUpdate(bookCategory);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * BooksCategory表删除数据 转发给业务层
     *
     * @param categoryId
     * @return
     */
    @DeleteMapping("/CategoryDelete/{categoryId}")
    public String categoryDelete(@PathVariable("categoryId") Long categoryId) {
        return booksCategoryService.categoryDelete(categoryId);
    }
}

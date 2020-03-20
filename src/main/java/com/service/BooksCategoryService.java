package com.service;

import com.entity.BooksCategory;
import com.github.pagehelper.PageInfo;

public interface BooksCategoryService {

    PageInfo<BooksCategory> categoryQueryAll(Integer pageNum, Integer pageSize, String searchCategory);

    String categoryInsert(BooksCategory bookCategory);

    BooksCategory categoryQueryOne(Long categoryId);

    int categoryUpdate(BooksCategory bookCategory);

    String categoryDelete(Long categoryId);

}

package com.service.impl;

import com.config.ReturnVariable;
import com.entity.Books;
import com.entity.BooksCategory;
import com.entity.UserBook;
import com.entity.UserVisit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.BooksCategoryMapper;
import com.mapper.BooksMapper;
import com.mapper.UserBookMapper;
import com.mapper.UserVisitMapper;
import com.service.BooksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired(required = false)
    private BooksMapper booksMapper;

    @Autowired(required = false)
    private BooksCategoryMapper booksCategoryMapper;

    @Autowired(required = false)
    private UserBookMapper userBookMapper;

    @Autowired(required = false)
    private UserVisitMapper userVisitMapper;

    /**
     * 处理书籍信息分页、模糊查询请求 访问数据库 并返回查询结果
     *
     * @param pageNum
     * @param pageSize
     * @param searchBook
     * @return
     */
    @Override
    public PageInfo<Books> booksQueryAll(Integer pageNum, Integer pageSize, String searchBook) {
        //如果搜索框关键字部位空则模糊查询
        Example example = new Example(Books.class);
        if (StringUtils.isNotBlank(searchBook)) {
            example.createCriteria().andLike("bookName", "%" + searchBook + "%");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Books> books = booksMapper.selectByExample(example);
        for (Books book : books) {
            BooksCategory booksCategory = booksCategoryMapper.selectByPrimaryKey(book.getCategoryId());
            book.setCategoryName(booksCategory.getCategoryName());
        }
        return new PageInfo<>(books);
    }

    /**
     * 处理书籍信息添加请求 访问数据库 并返回添加结果
     *
     * @param book
     * @return
     */
    @Transactional
    @Override
    public int booksInsert(Books book) {
        //由于前台添加时只传了总册数没有库存 所有在这里把库存加上(添加时总册数=库存数)
        book.setBookInventory(book.getBookGross());
        return booksMapper.insert(book);
    }

    /**
     * 处理书籍删除请求 访问数据库 并返回删除结果
     *
     * @param bookId
     * @return
     */
    @Transactional
    @Override
    public int booksDelete(Long bookId) {
        return booksMapper.deleteByPrimaryKey(bookId);
    }

    /**
     * 处理查询一个书籍信息请求 访问数据库 并返回查询结果
     *
     * @param bookId
     * @return
     */
    @Override
    public Books booksQueryOne(Long bookId) {
        return booksMapper.selectByPrimaryKey(bookId);
    }

    /**
     * 处理书籍修信息改请求 访问数据库 并返回修改结果
     *
     * @param book
     * @param num
     * @return
     */
    @Transactional
    @Override
    public int booksUpdate(Books book, Long num) {
        //用新总册数减去原总册数然后再加给库存
        Long number = book.getBookGross() - num;
        book.setBookInventory(book.getBookInventory() + number);
        return booksMapper.updateByPrimaryKey(book);
    }

    /**
     * 处理书籍上下架标记请求 访问数据库 并返回修改
     *
     * @param book
     * @return
     */
    @Transactional
    @Override
    public int booksEditSign(Books book) {
        return booksMapper.updateByPrimaryKeySelective(book);
    }

    /**
     * 处理用户借阅书籍信息分页、模糊查询请求 访问数据库 并返回查询结果
     *
     * @param pageNum
     * @param pageSize
     * @param bookSign
     * @param searchBook
     * @return
     */
    @Override
    public PageInfo<Books> borrowBooks(Integer pageNum, Integer pageSize, Boolean bookSign, String searchBook) {
        //如果搜索框关键字部位空则模糊查询
        Example example = new Example(Books.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookSign", bookSign);
        if (StringUtils.isNotBlank(searchBook)) {
            criteria.andLike("bookName", "%" + searchBook + "%");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Books> books = booksMapper.selectByExample(example);
        for (Books book : books) {
            BooksCategory booksCategory = booksCategoryMapper.selectByPrimaryKey(book.getCategoryId());
            book.setCategoryName(booksCategory.getCategoryName());
        }
        return new PageInfo<>(books);
    }

    /**
     * 处理用户借阅书籍请求 今日借阅量+1  图书库存-1  访问数据库 并返回添加结果
     *
     * @param userBook
     * @return
     */
    @Transactional
    @Override
    public String borrow(UserBook userBook) {
        UserVisit userVisit = userVisitMapper.selectUserVisitLastOne();
        userVisit.setVisitBorrow(userVisit.getVisitBorrow() + 1);
        userVisitMapper.updateByPrimaryKey(userVisit);
        Books book = booksMapper.selectByPrimaryKey(userBook.getBookId());
        book.setBookInventory(book.getBookInventory() - 1);
        booksMapper.updateByPrimaryKey(book);
        int insert = userBookMapper.insert(userBook);
        if (insert == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * 处理根据用户Id 查询当前借阅得图书Id 访问数据库 并返回查询结果
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> userBook(Long userId) {
        return userBookMapper.selectBookIdByUserId(userId);
    }

    /**
     * 处理用户借阅书籍请求 访问数据库 并返回查询结果
     *
     * @param pageNum
     * @param pageSize
     * @param searchBook
     * @return
     */
    @Override
    public PageInfo<UserBook> myBooks(Integer pageNum, Integer pageSize, Long userId, String searchBook) {
        //如果搜索框关键字部位空则模糊查询
        Example example = new Example(UserBook.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        if (StringUtils.isNotBlank(searchBook)) {
            Example example1 = new Example(Books.class);
            List<Long> bookIds = new ArrayList<>();
            example1.createCriteria().andLike("bookName", "%" + searchBook + "%");
            List<Books> booksList = booksMapper.selectByExample(example1);
            for (Books book : booksList) {
                bookIds.add(book.getBookId());
            }
            if (bookIds != null && bookIds.size() > 0) {
                criteria.andIn("bookId", bookIds);
            } else {
                return null;
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<UserBook> userBooks = userBookMapper.selectByExample(example);
        for (UserBook userBook : userBooks) {
            Books books = booksMapper.selectByPrimaryKey(userBook.getBookId());
            BooksCategory booksCategory = booksCategoryMapper.selectByPrimaryKey(books.getCategoryId());
            books.setCategoryName(booksCategory.getCategoryName());
            userBook.setBooks(books);
        }
        return new PageInfo<>(userBooks);
    }

    /**
     * 处理用户还书请求 返回库存 访问数据库 返回删除结果
     *
     * @param borrowId
     * @return
     */
    @Transactional
    @Override
    public int returnBook(Long borrowId) {
        UserBook userBook = userBookMapper.selectByPrimaryKey(borrowId);
        Books book = booksMapper.selectByPrimaryKey(userBook.getBookId());
        book.setBookInventory(book.getBookInventory() + 1);
        booksMapper.updateByPrimaryKey(book);
        return userBookMapper.deleteByPrimaryKey(borrowId);
    }
}

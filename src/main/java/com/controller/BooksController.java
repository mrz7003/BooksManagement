package com.controller;

import com.config.ReturnVariable;
import com.entity.Books;
import com.entity.UserBook;
import com.github.pagehelper.PageInfo;
import com.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


/**
 * 接收Books表的请求并处理
 */
@RestController
@RequestMapping("/Books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    /**
     * Books表的分页、模糊查询 转发给业务层
     *
     * @param pageNum
     * @param pageSize
     * @param searchBook
     * @return
     */
    @GetMapping("/BooksQuery")
    public PageInfo<Books> booksQueryAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(required = false) String searchBook) {
        PageInfo<Books> pageInfo = booksService.booksQueryAll(pageNum, pageSize, searchBook);
        if (pageInfo != null && pageInfo.getSize() > 0) {
            return pageInfo;
        }
        return null;
    }

    /**
     * Books表添加数据 转发给业务层
     *
     * @param book
     * @return
     */
    @PostMapping("/BooksInsert")
    public String booksInsert(Books book) {
        int i = booksService.booksInsert(book);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * Books表删除数据 转发给业务层
     *
     * @param bookId
     * @return
     */
    @DeleteMapping("/BooksDelete/{bookId}")
    public String bookDelete(@PathVariable("bookId") Long bookId) {
        int i = booksService.booksDelete(bookId);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * Books表查询一个书籍信息 转发给业务层
     *
     * @param bookId
     * @return
     */
    @GetMapping("/BooksQueryOne")
    public Books booksQueryOne(@RequestParam Long bookId) {
        Books books = booksService.booksQueryOne(bookId);
        if (books != null) {
            return books;
        }
        return null;
    }

    /**
     * Books表修改数据 转发给业务层
     *
     * @param book
     * @param num
     * @return
     */
    @PutMapping("/BooksUpdate")
    public String booksUpdate(Books book, @RequestParam Long num) {
        int i = booksService.booksUpdate(book, num);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * Books表修改上架下架标记 转发给业务层
     *
     * @param book
     * @return
     */
    @PutMapping("/BooksEditSign")
    public String booksEditSign(Books book) {
        int i = booksService.booksEditSign(book);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

    /**
     * Books表的分页、模糊、已上架图书的查询 转发给业务层
     *
     * @param pageNum
     * @param pageSize
     * @param bookSign
     * @param searchBook
     * @return
     */
    @GetMapping("/BorrowBooks")
    public PageInfo<Books> borrowBooks(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam Boolean bookSign, @RequestParam(required = false) String searchBook) {
        PageInfo<Books> booksPageInfo = booksService.borrowBooks(pageNum, pageSize, bookSign, searchBook);
        if (booksPageInfo != null && booksPageInfo.getSize() > 0) {
            return booksPageInfo;
        }
        return null;
    }

    /**
     * 接收用户借阅图书请求 转发给业务层
     *
     * @param userBook
     * @return
     */
    @PutMapping("/Borrow")
    public String borrow(UserBook userBook) {
        return booksService.borrow(userBook);
    }

    /**
     * 接收查询user_book表图书ID查询请求 转发给业务层
     *
     * @return
     */
    @GetMapping("/UserBook")
    public List<Long> userBook(@RequestParam Long userId) {
        List<Long> bookIds = booksService.userBook(userId);
        if (bookIds != null && bookIds.size() > 0) {
            return bookIds;
        }
        return null;
    }

    /**
     * 接收用户已借阅数据查询请求 转发给业务层
     *
     * @param pageNum
     * @param pageSize
     * @param searchBook
     * @return
     */
    @GetMapping("/MyBooks")
    public PageInfo<UserBook> myBooks(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam Long userId, @RequestParam(required = false) String searchBook) {
        PageInfo<UserBook> userBookPageInfo = booksService.myBooks(pageNum, pageSize, userId, searchBook);
        if (userBookPageInfo != null && userBookPageInfo.getSize() > 0) {
            return userBookPageInfo;
        }
        return null;
    }

    /**
     * 接收用户还书请求 转发给业务层
     *
     * @param borrowId
     * @return
     */
    @DeleteMapping("/ReturnBook/{borrowId}")
    public String returnBook(@PathVariable("borrowId") Long borrowId) {
        int i = booksService.returnBook(borrowId);
        if (i == 1) {
            return ReturnVariable.RETURN_SUCCESS;
        }
        return ReturnVariable.RETURN_ERROR;
    }

}

package com.service;

import com.entity.Books;
import com.entity.UserBook;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BooksService {

    PageInfo<Books> booksQueryAll(Integer pageNum, Integer pageSize, String searchBook);

    int booksInsert(Books book);

    int booksDelete(Long bookId);

    Books booksQueryOne(Long bookId);

    int booksUpdate(Books book, Long num);

    int booksEditSign(Books book);

    PageInfo<Books> borrowBooks(Integer pageNum, Integer pageSize, Boolean bookSign, String searchBook);

    String borrow(UserBook userBook);

    List<Long> userBook(Long userId);

    PageInfo<UserBook> myBooks(Integer pageNum, Integer pageSize, Long userId, String searchBook);

    int returnBook(Long borrowId);
}

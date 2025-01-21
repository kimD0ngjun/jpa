package com.example.jpa.queryDsl.repository.book;

import com.example.jpa.queryDsl.entity.Book;

import java.util.List;

public interface CustomBookRepository {

    // 조건(Where)
    Book findBookByTitle(String title);

    // 페이지네이션
    List<Book> findBookByPage(int offset, int limit);
}

package com.example.jpa.queryDsl.repository.book;

import com.example.jpa.queryDsl.entity.Book;

public interface CustomBookRepository {

    // 조건(Where)
    Book findBookByTitle(String title);
}

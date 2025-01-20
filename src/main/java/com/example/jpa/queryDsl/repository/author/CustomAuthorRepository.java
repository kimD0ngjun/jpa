package com.example.jpa.queryDsl.repository.author;

import com.example.jpa.queryDsl.entity.Author;

import java.util.List;

public interface CustomAuthorRepository {

    List<Author> findAuthorByCondition();

}

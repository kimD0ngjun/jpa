package com.example.jpa.queryDsl.repository.author;

import com.example.jpa.queryDsl.entity.Author;
import com.querydsl.core.Tuple;

import java.util.List;

public interface CustomAuthorRepository {

    List<Author> findAuthorByCondition();

    List<Tuple> findAuthorByGroup();

    // 간단한 라이트 조인
    List<Author> rightJoin();
}

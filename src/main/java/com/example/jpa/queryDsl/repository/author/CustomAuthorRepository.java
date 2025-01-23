package com.example.jpa.queryDsl.repository.author;

import com.example.jpa.queryDsl.entity.Author;
import com.querydsl.core.Tuple;

import java.util.List;

public interface CustomAuthorRepository {

    List<Author> findAuthorByCondition();

    List<Tuple> findAuthorByGroup();

    // 간단한 라이트 조인
    List<Author> rightJoin();

    // 간단한 서브쿼리 예시
    Author simpleSubquery();

    // Where + 서브쿼리
    Author whereSubquery();

    // Select * 서브쿼리
    List<Tuple> selectSubquery();
}

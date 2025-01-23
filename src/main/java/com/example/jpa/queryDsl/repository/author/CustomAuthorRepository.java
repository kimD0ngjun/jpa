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

    /**
     * From + 서브쿼리는 불가능하다
     * JPA 표준에서 하위 쿼리의 FROM 절에서는 엔터티나 엔터티 경로만 사용할 수 있으며 서브쿼리를 지원하지 않는다.
     * QueryDSL은 태생적으로 JPA에서 발전된 기술이기 때문에 기존의 이러한 제약을 따른다.
     */
}

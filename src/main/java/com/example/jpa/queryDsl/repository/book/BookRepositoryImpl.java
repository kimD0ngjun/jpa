package com.example.jpa.queryDsl.repository.book;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookRepositoryImpl implements CustomBookRepository {

    private final JPAQueryFactory queryFactory;

}

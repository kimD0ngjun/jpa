package com.example.jpa.queryDsl.repository.author;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorRepositoryImpl implements CustomAuthorRepository {

    private final JPAQueryFactory queryFactory;

}

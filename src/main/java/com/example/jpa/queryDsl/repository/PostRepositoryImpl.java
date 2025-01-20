package com.example.jpa.queryDsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * QueryDSL 사용을 위한 세팅 - QueryDSL 구현체이므로 QueryDSL과 관련된 코드를 여기에 작성
 */
@RequiredArgsConstructor
public class PostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory queryFactory;

}

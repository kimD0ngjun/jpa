package com.example.jpa.queryDsl.repository.organization;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrganizationRepositoryImpl implements CustomOrganizationRepository {

    private final JPAQueryFactory queryFactory;

}

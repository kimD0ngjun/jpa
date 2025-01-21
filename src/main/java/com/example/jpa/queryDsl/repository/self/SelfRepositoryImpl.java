package com.example.jpa.queryDsl.repository.self;

import com.example.jpa.queryDsl.entity.QSelf;
import com.example.jpa.queryDsl.entity.Self;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SelfRepositoryImpl implements CustomSelfRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Self> selfJoin() {
        QSelf self1 = QSelf.self1;
        QSelf self2 = new QSelf("self2");

        return queryFactory.
                selectFrom(self1).
                leftJoin(self1.self, self2).
                fetch();
    }
}

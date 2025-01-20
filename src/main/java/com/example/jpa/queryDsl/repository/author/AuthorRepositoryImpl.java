package com.example.jpa.queryDsl.repository.author;

import com.example.jpa.queryDsl.entity.Author;
import com.example.jpa.queryDsl.entity.QAuthor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AuthorRepositoryImpl implements CustomAuthorRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Author> findAuthorByCondition() {
        QAuthor author = QAuthor.author;

        // 동일 여부
        author.name.eq("John"); // 일치
        author.name.ne("John"); // 일치 x
        author.name.isNotNull(); // Null x

        // 포함
        author.age.in(20, 30, 40); // 포함
        author.age.notIn(25, 35, 45); // 미포함

        // 문자열
        author.name.like("J%"); // LIKE : J로 시작
        author.name.startsWith("J"); // J로 시작
        author.name.contains("Jo"); // J 포함

        // 수 비교
        author.age.between(25, 35); // 25 ~ 35
        author.age.lt(30); // < 30
        author.age.loe(30); // <= 30
        author.age.gt(30); // > 30
        author.age.goe(30); // >= 30

        return queryFactory.selectFrom(author)
                .where(
                        author.age.notBetween(20, 30)
                                .and(author.age.gt(10))
                                .and(author.age.lt(50))
                )
                .fetch();
    }
}

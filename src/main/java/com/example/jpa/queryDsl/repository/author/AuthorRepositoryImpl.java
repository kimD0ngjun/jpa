package com.example.jpa.queryDsl.repository.author;

import com.example.jpa.queryDsl.entity.Author;
import com.example.jpa.queryDsl.entity.QAuthor;
import com.example.jpa.queryDsl.entity.QBook;
import com.querydsl.core.Tuple;
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
        author.name.like("A%"); // LIKE : A로 시작
        author.name.startsWith("A"); // J로 시작
        author.name.contains("1"); // 1 포함
        author.name.endsWith("7"); // 7로 끝남

        // 수 비교
        author.age.between(25, 35); // 25 ~ 35
        author.age.lt(30); // < 30
        author.age.loe(30); // <= 30
        author.age.gt(30); // > 30
        author.age.goe(30); // >= 30

        return queryFactory.selectFrom(author)
                .where(
                        (
                                author.age.notBetween(20, 30)
                                .and(author.age.gt(10))
                                .and(author.age.lt(50))
                        ).or(
                                author.name.contains("1")
                        )
                )
                .fetch();
    }

    @Override
    public List<Tuple> findAuthorByGroup() {
        QAuthor author = QAuthor.author;

        // 조직 별로 '저자 총 수'와 '저자의 평균 나이' 계산 후, 저자 평균 나이가 10 나온 경우만 반환
        return queryFactory
                .select(author.organization.orgName, author.count(), author.age.avg())
                .from(author)
                .groupBy(author.organization.id)
                .having(author.age.avg().gt(10))
                .fetch();
    }

    @Override
    public List<Author> rightJoin() {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;

        return queryFactory
                .selectFrom(author)
                .rightJoin(author.book, book)
                .fetch();
    }
}

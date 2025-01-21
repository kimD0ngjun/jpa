package com.example.jpa.queryDsl.repository.book;

import com.example.jpa.queryDsl.entity.Book;
import com.example.jpa.queryDsl.entity.QBook;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookRepositoryImpl implements CustomBookRepository {

    private final JPAQueryFactory queryFactory;

    // 간단한 조건
    @Override
    public Book findBookByTitle(String title) {
        QBook book = QBook.book;

        return queryFactory.selectFrom(book).where(book.title.eq(title)).fetchOne();
    }

    @Override
    public List<Book> findBookByPage(int offset, int limit) {
        QBook book = QBook.book;

        QueryResults<Book> res = queryFactory
                .selectFrom(book)
                .offset(offset)
                .limit(limit)
                .fetchResults(); // 페이지네이션 정보까지 같이 조회

        System.out.println("Total : " + res.getTotal());
        System.out.println("Limit : " + res.getLimit());
        System.out.println("Offset : " + res.getOffset());

        return res.getResults();
    }
}

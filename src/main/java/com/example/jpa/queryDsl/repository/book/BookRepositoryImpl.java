package com.example.jpa.queryDsl.repository.book;

import com.example.jpa.queryDsl.entity.Book;
import com.example.jpa.queryDsl.entity.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookRepositoryImpl implements CustomBookRepository {

    private final JPAQueryFactory queryFactory;

    // 간단한 조건
    @Override
    public Book findBookByTitle(String title) {
        QBook book = QBook.book;

        return queryFactory.selectFrom(book).where(book.title.eq(title)).fetchOne();
    }

    // 다양한 조건들
}

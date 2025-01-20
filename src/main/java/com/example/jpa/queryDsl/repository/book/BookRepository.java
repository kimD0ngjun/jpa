package com.example.jpa.queryDsl.repository.book;

import com.example.jpa.queryDsl.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

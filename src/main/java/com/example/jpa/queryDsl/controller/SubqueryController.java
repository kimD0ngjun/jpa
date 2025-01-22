package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.entity.Author;
import com.example.jpa.queryDsl.entity.Book;
import com.example.jpa.queryDsl.repository.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubqueryController {

    private final AuthorRepository authorRepository;

    @GetMapping("/sub1")
    public void simpleSubquery() {
        Author author = authorRepository.simpleSubquery();
        System.out.println("작가명 : " + author.getName() +
                ", 책 리스트 : " + author.getBook().stream().map(Book::getTitle).toList());
    }
}

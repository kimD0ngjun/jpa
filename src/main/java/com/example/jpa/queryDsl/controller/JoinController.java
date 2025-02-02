package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.entity.Book;
import com.example.jpa.queryDsl.repository.author.AuthorRepository;
import com.example.jpa.queryDsl.repository.book.BookRepository;
import com.example.jpa.queryDsl.repository.self.SelfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final SelfRepository selfRepository;

    @GetMapping("/join1")
    public void test1() {
        System.out.println(bookRepository.leftJoin()
                .stream()
                .map(e -> "제목 : " + e.getTitle() + ", 작가 : " + e.getAuthor().getName() + "\n")
                .toList());
    }

    @GetMapping("/join2")
    public void test2() {
        System.out.println(authorRepository.rightJoin()
                .stream()
                .map(e -> "작가 : " + e.getName() + ", 제목들 : " + e.getBook().stream().map(Book::getTitle).toList() + "\n")
                .toList());
    }

    @GetMapping("/join3")
    public void test3() {
        System.out.println(bookRepository.fetchJoin()
                .stream()
                .map(e -> "제목 : " + e.getTitle() + ", 작가 : " + e.getAuthor().getName() + "\n")
                .toList());
    }

    @GetMapping("/join4")
    public void test4() {
        System.out.println(selfRepository.selfJoin()
                .stream()
                .map(e -> "이름 : " + e.getName() + "\n")
                .toList());
    }
}

package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final BookRepository bookRepository;

    @GetMapping("/join1")
    public void test1() {
        System.out.println(bookRepository.leftJoin()
                .stream()
                .map(e -> "제목 : " + e.getTitle() + ", 작가 : " + e.getAuthor().getName() + "\n")
                .toList());
    }
}

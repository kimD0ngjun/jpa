package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.dynamic.DynamicDTOMap;
import com.example.jpa.queryDsl.dynamic.DynamicDTOFactory;
import com.example.jpa.queryDsl.entity.Author;
import com.example.jpa.queryDsl.entity.Book;
import com.example.jpa.queryDsl.repository.author.AuthorRepository;
import com.example.jpa.queryDsl.repository.book.BookRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final DynamicDTOFactory dynamicDTOFactory;

    @GetMapping("/1/{title}")
    public Book test1(@PathVariable(value = "title") String title) {
        return bookRepository.findBookByTitle(title);
    }

    @GetMapping("/2")
    public List<Author> test2() {
        return authorRepository.findAuthorByCondition();
    }

    @GetMapping("/3")
    public List<DynamicDTOMap> test3() {
        List<Tuple> tuples = authorRepository.findAuthorByGroup();
        return tuples.stream().map(dynamicDTOFactory::createDTOMap).toList();
    }

    @GetMapping("/4")
    public void test4() {
        System.out.println("결과 : " + bookRepository.findBookByPage(1, 3).stream().map(Book::getTitle).toList());
    }
}

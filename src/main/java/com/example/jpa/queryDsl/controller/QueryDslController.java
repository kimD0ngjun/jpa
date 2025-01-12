package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.entity.Post;
import com.example.jpa.queryDsl.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryDslController {

    private final PostRepository postRepository;

    @GetMapping("/find/{id}")
    public Post findPostById(@PathVariable("id") Long id) {
        return postRepository.getQslPost(id);
    }
}

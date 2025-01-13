package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.dto.PostCountDTO;
import com.example.jpa.queryDsl.entity.Post;
import com.example.jpa.queryDsl.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueryDslController {

    private final PostRepository postRepository;

    @GetMapping("/find/{id}")
    public Post findPostById(@PathVariable("id") Long id) {
        return postRepository.getQslPost(id);
    }

    @GetMapping("/find1")
    public List<PostCountDTO> getQslPostsByTitleGroupingAndHaving() {
        return postRepository.getQslPostsByTitleGroupingAndHaving();
    }

    @GetMapping("/find2")
    public List<Post> getQslPostsWithInnerJoinAndSubquery() {
        return postRepository.getQslPostsWithInnerJoinAndSubquery();
    }

    @GetMapping("/find3/{limit}")
    public List<Post> getQslDistinctPostsLimited(@PathVariable("limit") int limit) {
        return postRepository.getQslDistinctPostsLimited(limit);
    }


}

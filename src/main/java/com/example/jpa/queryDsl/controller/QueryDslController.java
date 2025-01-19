package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.dto.PostCountDTO;
import com.example.jpa.queryDsl.entity.Post;
import com.example.jpa.queryDsl.service.PostService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueryDslController {

    private final PostService postService;

    @GetMapping("/find/{id}")
    public Post findPostById(@PathVariable("id") Long id) {
        return postService.findPostById(id);
    }

    @GetMapping("/find1")
    public List<PostCountDTO> getQslPostsByTitleGroupingAndHaving() {
        return postService.getQslPostsByTitleGroupingAndHaving();
    }

    @GetMapping("/find2")
    public List<Post> getQslPostsWithInnerJoinAndSubquery() {
        return postService.getQslPostsWithInnerJoinAndSubquery();
    }

    @GetMapping("/find3/{limit}")
    public List<Post> getQslDistinctPostsLimited(@PathVariable("limit") int limit) {
        return postService.getQslDistinctPostsLimited(limit);
    }

    @GetMapping("/find4")
    public List<Tuple> getQslPostsWithConditionalGrouping() {
        return postService.getQslPostsWithConditionalGrouping();
    }

    @GetMapping("/find5")
    public List<Tuple> getQslPostsWithComplexJoinAndGrouping() {
        return postService.getQslPostsWithComplexJoinAndGrouping();
    }
}

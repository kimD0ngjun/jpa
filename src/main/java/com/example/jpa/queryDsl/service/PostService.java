package com.example.jpa.queryDsl.service;

import com.example.jpa.queryDsl.dto.PostCountDTO;
import com.example.jpa.queryDsl.entity.Post;
import com.example.jpa.queryDsl.repository.PostRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post findPostById(Long id) {
        return postRepository.getQslPost(id);
    }

    public List<PostCountDTO> getQslPostsByTitleGroupingAndHaving() {
        return postRepository.getQslPostsByTitleGroupingAndHaving();
    }

    public List<Post> getQslPostsWithInnerJoinAndSubquery() {
        return postRepository.getQslPostsWithInnerJoinAndSubquery();
    }

    public List<Post> getQslDistinctPostsLimited(int limit) {
        return postRepository.getQslDistinctPostsLimited(limit);
    }

    public List<Tuple> getQslPostsWithConditionalGrouping() {
        return postRepository.getQslPostsWithConditionalGrouping();
    }

    public List<Tuple> getQslPostsWithComplexJoinAndGrouping() {
        return postRepository.getQslPostsWithComplexJoinAndGrouping();
    }
}

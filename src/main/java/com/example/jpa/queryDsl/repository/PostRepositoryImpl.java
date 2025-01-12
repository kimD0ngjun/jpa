package com.example.jpa.queryDsl.repository;

import com.example.jpa.queryDsl.entity.Post;
import com.example.jpa.queryDsl.entity.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * QueryDSL 사용을 위한 세팅 - QueryDSL 구현체이므로 QueryDSL과 관련된 코드를 여기에 작성
 */
@RequiredArgsConstructor
public class PostRepositoryImpl implements CustomPostRepository{

    // 여기서 구현된 코드들을 PostRepository에서 받아 사용할 수 있게 한다
    private final JPAQueryFactory queryFactory;

    /**
     * SELECT * FROM post WHERE post_id = ?
     */
    @Override
    public Post getQslPost(Long id) {
        QPost qpost = QPost.post;

        return queryFactory.
                selectFrom(qpost). // SELECT * FROM post
                where(qpost.id.eq(id)). // WHERE post_id = ?
                fetchOne(); // 단일 결과를 반환하고 없으면 null 반환
    }
}

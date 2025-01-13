package com.example.jpa.queryDsl.repository;

import com.example.jpa.queryDsl.entity.Post;
import com.example.jpa.queryDsl.entity.QPost;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    /**
     * SELECT title, COUNT(*) AS post_count
     * FROM post
     * WHERE content LIKE '%apple%'
     * GROUP BY title
     * HAVING COUNT(*) > 1
     * ORDER BY post_count DESC;
     */
    @Override
    public List<Tuple> getQslPostsByTitleGroupingAndHaving() {
        return List.of();
    }

    /**
     * SELECT p1.title, p1.content
     * FROM post p1
     * JOIN (
     *     SELECT content
     *     FROM post
     *     GROUP BY content
     *     HAVING COUNT(*) > 1
     * ) p2 ON p1.content = p2.content
     * ORDER BY p1.title;
     */
    @Override
    public List<Post> getQslPostsWithInnerJoinAndSubquery() {
        return List.of();
    }

    /**
     * SELECT DISTINCT title, content
     * FROM post
     * ORDER BY title ASC, content DESC
     * LIMIT 'limit';
     */
    @Override
    public List<Post> getQslDistinctPostsLimited(int limit) {
        return List.of();
    }

    /**
     * SELECT
     *     CASE
     *         WHEN content LIKE '%apple%' THEN 'APPLE GROUP'
     *         WHEN content LIKE '%banana%' THEN 'BANANA GROUP'
     *         ELSE 'OTHER GROUP'
     *     END AS content_group,
     *     COUNT(*) AS group_count
     * FROM post
     * GROUP BY content_group
     * ORDER BY group_count DESC;
     */
    @Override
    public List<Tuple> getQslPostsWithConditionalGrouping() {
        return List.of();
    }

    /**
     * SELECT p1.title, p1.content, COUNT(p2.id) AS similar_count
     * FROM post p1
     * JOIN post p2
     *     ON p1.content = p2.content AND p1.id != p2.id
     * WHERE p1.content LIKE '%apple%' OR p1.content LIKE '%banana%'
     * GROUP BY p1.title, p1.content
     * HAVING COUNT(p2.id) > 2
     * ORDER BY similar_count DESC;
     */
    @Override
    public List<Tuple> getQslPostsWithComplexJoinAndGrouping() {
        return List.of();
    }
}

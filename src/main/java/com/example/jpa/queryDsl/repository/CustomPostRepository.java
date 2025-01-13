package com.example.jpa.queryDsl.repository;

import com.example.jpa.queryDsl.dto.PostCountDTO;
import com.example.jpa.queryDsl.entity.Post;
import com.querydsl.core.Tuple;

import java.util.List;

/**
 * 사용자 정의 쿼리 메서드 인터페이스
 */
public interface CustomPostRepository {
    // id를 기반으로 원하는 특정 레코드 조회
    Post getQslPost(Long id);

    // content 필드에 "apple"이 포함된 레코드들 중
    // 해당 레코드들의 title이 같은 게 2개 이상 존재하면 title 별로 그룹화하고 속하는 title 수가 많은 순서로 정렬
    List<PostCountDTO> getQslPostsByTitleGroupingAndHaving();

    // content가 같은 레코드가 2개 이상이면
    // 해당 content의 레코드를 title 오름차순 기준으로 정렬 반환
    List<Post> getQslPostsWithInnerJoinAndSubquery();

    // 중복 없이 title과 content 조합이 고유한 상위 limit 카운트개 레코드를
    // title 오름차순, content 내림차순으로 정렬 반환
    List<Post> getQslDistinctPostsLimited(int limit);

    // content에 "apple"이 포함되면 "APPLE GROUP" 분류
    // content에 "banana"가 포함되면 "BANANA GROUP" 분류
    // content에 둘 다 아니라면 "OTHER GROUP" 분류
    // 각 분류 레코드 수가 많은 순으로 정렬
    List<Tuple> getQslPostsWithConditionalGrouping();

    // content에 "apple"이나 "banana"가 포함된 게시물
    // 자기 자신을 제외하고 동일한 content를 가진 다른 게시물이 2개 이상 존재하는 경우
    // title과 content를 반환하면서, 같은 content를 가진 게시물 수를 출력
    // 유사한 게시물이 많은 순서로 정렬
    List<Tuple> getQslPostsWithComplexJoinAndGrouping();
}

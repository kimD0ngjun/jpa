package com.example.jpa.queryDsl.repository;

import com.example.jpa.queryDsl.entity.Post;

/**
 * 사용자 정의 쿼리 메서드 인터페이스
 */
public interface CustomPostRepository {
    Post getQslPost(Long id);
}

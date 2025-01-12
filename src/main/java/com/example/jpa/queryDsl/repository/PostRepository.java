package com.example.jpa.queryDsl.repository;

import com.example.jpa.queryDsl.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository 인터페이스 (기본 CRUD 제공)
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
}

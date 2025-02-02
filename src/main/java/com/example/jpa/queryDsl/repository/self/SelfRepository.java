package com.example.jpa.queryDsl.repository.self;

import com.example.jpa.queryDsl.entity.Self;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfRepository extends JpaRepository<Self, Long>, CustomSelfRepository {
}

package com.example.persistenceContext.transaction.repository;

import com.example.persistenceContext.transaction.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
}

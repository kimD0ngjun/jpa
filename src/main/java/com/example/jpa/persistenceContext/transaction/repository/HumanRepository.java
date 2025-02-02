package com.example.jpa.persistenceContext.transaction.repository;

import com.example.jpa.persistenceContext.transaction.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
}

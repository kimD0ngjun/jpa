package com.example.jpa.persistenceContext.objectpool.repository;

import com.example.jpa.persistenceContext.objectpool.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
}

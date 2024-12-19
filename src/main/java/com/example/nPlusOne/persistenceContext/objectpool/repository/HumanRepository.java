package com.example.nPlusOne.persistenceContext.objectpool.repository;

import com.example.nPlusOne.persistenceContext.objectpool.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
}

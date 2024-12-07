package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.oneToNToNN.One;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneRepository extends JpaRepository<One, Long> {

    // NamedEntityGraph를 사용하여 Fetch Join 동작
    @EntityGraph(value = "OneTwoThreeGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<One> findAll();
}

package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.oneToNToNN.One;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneRepository extends JpaRepository<One, Long> {

    @Query("SELECT DISTINCT o FROM One o " +
            "LEFT JOIN FETCH o.twoList t " +
            "LEFT JOIN FETCH t.threeList th " +
            "ORDER BY o.id, t.id, th.id")
    List<One> findAllWithTwoAndThree();

    // NamedEntityGraph를 사용하여 Fetch Join 동작
    @EntityGraph(attributePaths = {"twoList", "twoList.threeList"})
    List<One> findAllById(Long id);
}

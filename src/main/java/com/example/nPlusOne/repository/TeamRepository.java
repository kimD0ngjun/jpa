package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.oneToN.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t JOIN FETCH t.members")
    List<Team> findAllWithMembers();

    @Query("SELECT t FROM Team t JOIN FETCH t.members")
    Page<Team> findAllWithMembers(Pageable pageable);

//    @EntityGraph(attributePaths = "members") // 함께 조회하려는 연관관계 필드 명시
    List<Team> findAll();
}

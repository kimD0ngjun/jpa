package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.Team;
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
}

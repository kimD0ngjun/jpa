package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}

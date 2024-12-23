package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.oneToNToNN.Three;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreeRepository extends JpaRepository<Three, Long> {
}

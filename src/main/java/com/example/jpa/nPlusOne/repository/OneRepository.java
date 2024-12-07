package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.oneToNToNN.One;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneRepository extends JpaRepository<One, Long> {
}

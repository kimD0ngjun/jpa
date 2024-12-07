package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.B;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BRepository extends JpaRepository<B,Long> {
}

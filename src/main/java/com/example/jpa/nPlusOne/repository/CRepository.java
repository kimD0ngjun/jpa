package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRepository extends JpaRepository<C,Long> {
}

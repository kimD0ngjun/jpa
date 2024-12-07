package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ARepository extends JpaRepository<A,Long> {
}

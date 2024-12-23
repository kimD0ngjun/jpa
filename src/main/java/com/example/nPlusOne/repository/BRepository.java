package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.multiOneToN.B;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BRepository extends JpaRepository<B,Long> {
}

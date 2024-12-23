package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.multiOneToN.C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRepository extends JpaRepository<C,Long> {
}

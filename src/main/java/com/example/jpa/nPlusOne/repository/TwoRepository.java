package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.oneToNToNN.Two;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoRepository extends JpaRepository<Two, Long> {
}

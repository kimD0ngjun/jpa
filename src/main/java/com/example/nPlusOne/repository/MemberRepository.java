package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.oneToN.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}

package com.example.jpa.nPlusOne.repository;

import com.example.jpa.nPlusOne.entity.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ARepository extends JpaRepository<A,Long> {

    @Query("SELECT a FROM A a " +
            "LEFT JOIN FETCH a.bList b " +   // A와 B를 FetchJoin
            "LEFT JOIN FETCH a.cList c")     // A와 C를 FetchJoin
    List<A> findAllWithBAndC();
}

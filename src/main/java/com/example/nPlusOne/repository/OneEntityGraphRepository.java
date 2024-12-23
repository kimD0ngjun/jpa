package com.example.nPlusOne.repository;

import com.example.nPlusOne.entity.oneToNToNN.One;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OneEntityGraphRepository {

    @PersistenceContext
    private EntityManager em;

    public List<One> findAllWithEntityGraph() {
        TypedQuery<One> query = em.createQuery("SELECT o FROM One o", One.class);
        query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.One"));
        return query.getResultList();
    }
}

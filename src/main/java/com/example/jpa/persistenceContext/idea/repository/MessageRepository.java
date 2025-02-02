package com.example.jpa.persistenceContext.idea.repository;

import com.example.jpa.persistenceContext.idea.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}

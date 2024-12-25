package com.example.persistenceContext.idea.entity;

import com.example.persistenceContext.idea.annotation.ImmutableEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@ImmutableEntity
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one_id")
    private Long id;

    private String message;

    public Message(String message) {
        this.message = message;
    }
}

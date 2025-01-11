package com.example.jpa.persistenceContext.idea.entity;

import com.example.jpa.persistenceContext.idea.annotation.ImmutableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

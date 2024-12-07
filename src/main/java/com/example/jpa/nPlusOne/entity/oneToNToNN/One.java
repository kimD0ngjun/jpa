package com.example.jpa.nPlusOne.entity.oneToNToNN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one_id")
    private Long id;

    private String name;
}

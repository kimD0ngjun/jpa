package com.example.jpa.nPlusOne.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Setter
    @ManyToOne // ~ToOne: EAGER
    private Team team;   // 부모 : 자식 = 1 :

    public Member(String name) {
        this.name = name;
    }
}

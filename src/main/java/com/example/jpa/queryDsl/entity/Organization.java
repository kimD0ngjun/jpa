package com.example.jpa.queryDsl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organization")
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgName;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

}

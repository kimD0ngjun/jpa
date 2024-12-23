package com.example.nPlusOne.entity.multiOneToN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "a")
    private List<B> bList = new ArrayList<>();
//    private Set<B> bList = new HashSet<>();

    @OneToMany(mappedBy = "a")
    private List<C> cList = new ArrayList<>();
//    private Set<C> cList = new HashSet<>();

    public A(String name) {
        this.name = name;
    }

    public void addB(B b) {
        bList.add(b);
        b.setA(this);
    }

    public void addC(C c) {
        cList.add(c);
        c.setA(this);
    }
}

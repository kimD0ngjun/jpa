package com.example.jpa.nPlusOne.entity.oneToNToNN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Two {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "two_id")
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "one_id")
    private One one;

    @OneToMany(mappedBy = "two")
    private List<Three> threeList = new ArrayList<>();

    public Two(String name) {
        this.name = name;
    }

    public void addThree(Three three) {
        threeList.add(three);
        three.setTwo(this);
    }
}

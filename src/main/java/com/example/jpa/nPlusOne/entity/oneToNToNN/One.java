package com.example.jpa.nPlusOne.entity.oneToNToNN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@NamedEntityGraph(
        name = "OneTwoThreeGraph", // 그래프 이름
        attributeNodes = {
                @NamedAttributeNode(value = "twoList", subgraph = "two-to-three") // One → Two
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "two-to-three",
                        attributeNodes = {
                                @NamedAttributeNode("threeList") // Two → Three
                        }
                )
        }
)
public class One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "one")
    private List<Two> twoList = new ArrayList<>();

    public One(String name) {
        this.name = name;
    }

    public void addTwo(Two two) {
        twoList.add(two);
        two.setOne(this);
    }
}

package com.example.jpa.nPlusOne.entity.oneToN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    /**
     * , fetch = FetchType.EAGER : 모든 데이터 한번에 조회
     */
//    @BatchSize(size = 100) // batch size
    @OneToMany(mappedBy = "team") // ~ToMany: LAZY
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this); // 양방향 연관 관계 동기화
    }
}

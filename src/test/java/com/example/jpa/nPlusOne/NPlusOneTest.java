package com.example.jpa.nPlusOne;

import com.example.jpa.nPlusOne.entity.Member;
import com.example.jpa.nPlusOne.entity.Team;
import com.example.jpa.nPlusOne.repository.MemberRepository;
import com.example.jpa.nPlusOne.repository.TeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class NPlusOneTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

//    @DisplayName("팀 엔티티 생성 및 멤버 엔티티 생성과 연관관계 세팅")
//    @BeforeEach
//    void setUp() {
//        Team teamA = new Team("team_A");
//        teamRepository.save(teamA);
//
//        for (int i = 1; i <= 10; i++) {
//            Member member = new Member("member_" + i);
//            teamA.addMember(member);
//            memberRepository.save(member);
//        }
//
//        Team teamB = new Team("team_B");
//        teamRepository.save(teamB);
//
//        for (int i = 11; i <= 20; i++) {
//            Member member = new Member("member_" + i);
//            teamB.addMember(member);
//            memberRepository.save(member);
//        }
//
//        Team teamC = new Team("team_C");
//        teamRepository.save(teamC);
//
//        for (int i = 21; i <= 30; i++) {
//            Member member = new Member("member_" + i);
//            teamC.addMember(member);
//            memberRepository.save(member);
//        }
//    }

    @DisplayName("N+1 문제 테스트: 팀 조회")
    @Test
    void testNPlusOne() {
        List<String> list = teamRepository.findAll().stream()
                .flatMap(team -> team.getMembers().stream())
                .map(Member::getName)
                .toList();
    }
}

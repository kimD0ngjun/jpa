package com.example.jpa.nPlusOne;

import com.example.jpa.nPlusOne.entity.oneToNToNN.One;
import com.example.jpa.nPlusOne.entity.oneToNToNN.Three;
import com.example.jpa.nPlusOne.entity.oneToNToNN.Two;
import com.example.jpa.nPlusOne.repository.OneRepository;
import com.example.jpa.nPlusOne.repository.ThreeRepository;
import com.example.jpa.nPlusOne.repository.TwoRepository;
import com.example.jpa.nPlusOne.service.AService;
import com.example.jpa.nPlusOne.service.TeamMemberService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@Rollback(false)
public class NPlusOneTest {

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private AService aService;

//    @Autowired
//    private OneRepository oneRepository;
//
//    @Autowired
//    private TwoRepository twoRepository;
//
//    @Autowired
//    private ThreeRepository threeRepository;

//    @Autowired
//    private ARepository aRepository;
//
//    @Autowired
//    private BRepository bRepository;
//
//    @Autowired
//    private CRepository cRepository;

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

//    @Test
//    void setUp() {
//        A a1 = new A("a1");
//        aRepository.save(a1);
//
//        for (int i = 1; i <= 10; i++) {
//            if (i <= 5) {
//                B b = new B("b" + i);
//                bRepository.save(b);
//                a1.addB(b);
//            } else {
//                C c = new C("c" + i);
//                cRepository.save(c);
//                a1.addC(c);
//            }
//        }
//
//        A a2 = new A("a2");
//        aRepository.save(a2);
//
//        for (int i = 11; i <= 20; i++) {
//            if (i <= 15) {
//                B b = new B("b" + i);
//                bRepository.save(b);
//                a2.addB(b);
//            } else {
//                C c = new C("c" + i);
//                cRepository.save(c);
//                a2.addC(c);
//            }
//        }
//
//        A a3 = new A("a3");
//        aRepository.save(a3);
//
//        for (int i = 21; i <= 30; i++) {
//            if (i <= 25) {
//                B b = new B("b" + i);
//                bRepository.save(b);
//                a3.addB(b);
//            } else {
//                C c = new C("c" + i);
//                cRepository.save(c);
//                a3.addC(c);
//            }
//        }
//    }
//
//    @Test
//    void setUp() {
//        One one = new One("one1");
//        oneRepository.save(one);
//
//        for (int i=1; i<=3; i++) {
//            Two two = new Two("two" + i);
//            twoRepository.save(two);
//            one.addTwo(two);
//
//            for (int j=1; j<=3; j++) {
//                Three three = new Three("three" + j);
//                threeRepository.save(three);
//                two.addThree(three);
//            }
//        }
//    }

    @DisplayName("N+1 문제 테스트: 전체 팀 멤버 조회 by 팀 레포")
    @Test
    void testNPlusOneAllMembersByTeamRepo() {
        teamMemberService.findAllMembersByTeamRepo();
    }

    @DisplayName("N+1 문제 테스트: 전체 팀 멤버 조회 by 멤버 레포")
    @Test
    void testNPlusOneTeamMembersByMemberRepo() {
        teamMemberService.findAllMembersByMemberRepo();
    }

    @DisplayName("N+1 문제 테스트: 특정 팀 멤버 조회")
    @Test
    void testNPlusOneParticularMembers() {
        teamMemberService.findParticularMembers();
    }

    @DisplayName("Fetch 시점 비교용 임시 테스트")
    @Test
    void testFetchTime() {
        teamMemberService.findAllTeams();
    }

    @DisplayName("복수의 연관관계 N+1 문제 확인")
    @Test
    void testMultiNPlusOne() {
        aService.findAllBC();
    }

}

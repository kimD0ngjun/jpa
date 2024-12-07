package com.example.jpa.nPlusOne;

import com.example.jpa.nPlusOne.service.AService;
import com.example.jpa.nPlusOne.service.FetchJoinService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FetchJoinTest {

    @Autowired
    private FetchJoinService fetchJoinService;

    @Autowired
    private AService aService;

    @DisplayName("fetch join 기반 N+1 문제 해결 테스트")
    @Test
    void testFetchJoin() {
        fetchJoinService.findAllMembersByFetchJoin();
    }

    @DisplayName("JDBC 기반 join 문법 직접 활용을 통한 N+1 문제 해결 테스트")
    @Test
    void testJdbc() {
        fetchJoinService.findAllMembersByJdbc();
    }

    @DisplayName("페이징과 fetch join을 같이 사용하면 메모리 아웃 예외가 발생할 수 있다")
    @Test
    void testFetchJoinWithPaging() {
        fetchJoinService.findAllMembersWithPaging();
    }

    @DisplayName("복수의 연관관계 fetch join 확인")
    @Test
    void testMultiFetchJoin() {
        aService.findAllBCWithFetchJoin();
    }
}

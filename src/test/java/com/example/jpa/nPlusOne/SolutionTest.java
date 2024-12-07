package com.example.jpa.nPlusOne;

import com.example.jpa.nPlusOne.service.SolutionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SolutionTest {

    @Autowired
    private SolutionService solutionService;

    @DisplayName("fetch join 기반 N+1 문제 해결 테스트")
    @Test
    void testFetchJoin() {
        solutionService.findAllMembersByFetchJoin();
    }

    @DisplayName("JDBC 기반 join 문법 직접 활용을 통한 N+1 문제 해결 테스트")
    @Test
    void testJdbc() {
        solutionService.findAllMembersByJdbc();
    }
}

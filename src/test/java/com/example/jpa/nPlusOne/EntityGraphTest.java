package com.example.jpa.nPlusOne;

import com.example.jpa.nPlusOne.service.EntityGraphService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EntityGraphTest {

    @Autowired
    private EntityGraphService entityGraphService;

    @DisplayName("EntityGraph 기반 N+1 문제 해결 테스트")
    @Test
    void testEntityGraph() {
        entityGraphService.findAllMembersWithEntityGraph();
    }

}

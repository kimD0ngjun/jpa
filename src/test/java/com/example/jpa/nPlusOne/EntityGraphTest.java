package com.example.jpa.nPlusOne;

import com.example.jpa.nPlusOne.service.AService;
import com.example.jpa.nPlusOne.service.EntityGraphService;
import com.example.jpa.nPlusOne.service.OneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EntityGraphTest {

    @Autowired
    private EntityGraphService entityGraphService;

    @Autowired
    private OneService oneService;

    @Autowired
    private AService aService;

    @DisplayName("EntityGraph 기반 N+1 문제 해결 테스트")
    @Test
    void testEntityGraph() {
        entityGraphService.findAllMembersWithEntityGraph();
    }

    /**
     * 얘 역시
     * MultipleBagFetchException 을 감싸서 InvalidDataAccessApiUsageException 으로 반환
     * Set 구조로 필드를 바꾸면 중복을 허용치 않는 자료구조라서 되긴 하지만 그러면 순서 보장 x
     */
    @DisplayName("EntityGraph 기반 복수의 연관관계 N+1 확인")
    @Test
    void testABCEntityGraph() {
//        aService.findAllBCWithEntityGraph();

        Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class, // 반환하는 예외 클래스를 작성해야 함
                () -> aService.findAllBCWithEntityGraph()
        );
    }

    @DisplayName("EntityGraph 기반 연속된 연관관계 N+1 확인")
    @Test
    void testOneTwoThreeEntityGraph() {
//        oneService.findNumbersByEntityGraph();

        Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class, // 반환하는 예외 클래스를 작성해야 함
                () -> oneService.findNumbersByEntityGraph()
        );
    }
}
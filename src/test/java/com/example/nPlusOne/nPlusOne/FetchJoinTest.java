package com.example.nPlusOne.nPlusOne;

import com.example.jpa.nPlusOne.service.AService;
import com.example.jpa.nPlusOne.service.FetchJoinService;
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
public class FetchJoinTest {

    @Autowired
    private FetchJoinService fetchJoinService;

    @Autowired
    private OneService oneService;

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

    /**
     * Spring Data JPA 가 내부적으로
     * MultipleBagFetchException 을 감싸서 InvalidDataAccessApiUsageException 으로 변환
     */
    @DisplayName("연속된 연관관계 fetch join 확인")
    @Test
    void testContinuousFetchJoin() {
//        oneService.findALlNumbersByFetchJoin();

        Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class, // 반환하는 예외 클래스를 작성해야 함
                () -> oneService.findALlNumbersByFetchJoin()
        );
    }

    /**
     * Spring Data JPA 가 내부적으로
     * MultipleBagFetchException 을 감싸서 InvalidDataAccessApiUsageException 으로 변환
     */
    @DisplayName("복수의 연관관계 fetch join 확인")
    @Test
    void testMultiFetchJoin() {
//        aService.findAllBCWithFetchJoin();

        Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class, // 반환하는 예외 클래스를 작성해야 함
                () -> aService.findAllBCWithFetchJoin()
        );
    }
}

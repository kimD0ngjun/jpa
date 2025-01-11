package com.example.persistenceContext.transaction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class TransactionTest {

    @Autowired
    PlatformTransactionManager manager;

    @TestConfiguration
    static class Config {
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @DisplayName("트랜잭션 커밋 테스트")
    @Test
    void commit() {
        log.info("트랜잭션 시작");
        TransactionStatus status = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 커밋 시작");
        manager.commit(status);
        log.info("트랜잭션 커밋 완료");
    }

    @DisplayName("트랜잭션 롤백 테스트")
    @Test
    void rollback() {
        log.info("트랜잭션 시작");
        TransactionStatus status = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 롤백 시작");
        manager.rollback(status);
        log.info("트랜잭션 롤백 완료");
    }

    @DisplayName("연속 트랜잭션 커밋 테스트")
    @Test
    void continuousCommit() {
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋 시작");
        manager.commit(status1);
        log.info("트랜잭션1 커밋 완료");

        System.out.println();

        log.info("트랜잭션2 시작");
        TransactionStatus status2 = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 커밋 시작");
        manager.commit(status2);
        log.info("트랜잭션2 커밋 완료");
    }

    @DisplayName("트랜잭션 커밋 & 트랜잭션 롤백 테스트")
    @Test
    void commitAndRollback() {
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋 시작");
        manager.commit(status1);
        log.info("트랜잭션1 커밋 완료");

        System.out.println();

        log.info("트랜잭션2 시작");
        TransactionStatus status2 = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 롤백 시작");
        manager.rollback(status2);
        log.info("트랜잭션2 롤백 완료");
    }

    @DisplayName("트랜잭션 전파 : 외부 시작 -> 내부 시작 -> 내부 완료 -> 외부 완료")
    @Test
    void transactionPropagation() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = manager.getTransaction(new DefaultTransactionAttribute());
        // 외부 트랜잭션은 신규 트랜잭션 생성
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = manager.getTransaction(new DefaultTransactionAttribute());
        // 내부 트랜잭션은 신규 트랜잭션 생성이 되지 않음, 외부 트랜잭션에 참여
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());

        log.info("내부 트랜잭션 커밋");
        manager.commit(inner);

        log.info("외부 트랜잭션 커밋");
        manager.commit(outer);
    }

    @DisplayName("트랜잭션 전파 : 외부 시작 -> 내부 시작 -> 내부 완료 -> 외부 롤백")
    @Test
    void transactionPropagationOuterRollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = manager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = manager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 커밋");
        manager.commit(inner);  // 말은 커밋이지만, 일단 정상적으로 끝났다고 '마킹'되는 수

        log.info("외부 트랜잭션 롤백");
        manager.rollback(outer);  // 외부 트랜잭션이 커밋 혹은 롤백돼야 내부 트랜잭션 커밋 여부가 결정
    }

    @DisplayName("트랜잭션 전파 : 외부 시작 -> 내부 시작 -> 내부 롤백 -> 외부 커밋")
    @Test
    void transactionPropagationInnerRollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = manager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = manager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 롤백");
        manager.rollback(inner);  // 실제 물리(전체) 트랜잭션이 롤백하진 않고 기존 트랜잭션을 롤백 전용으로 마킹
        log.info("inner.getRollbackOnly: {}", inner.isRollbackOnly());

        log.info("외부 트랜잭션 커밋 시도");
        /**
         * 이 라인에서 UnexpectedRollbackException 발생
         * 커밋을 호출했지만 전체 트랜잭션이 롤백 전용으로 마킹되기 때문에 뮬라 트랜잭션을 롤백
         * 롤백 전용 표시가 true 임을 확인한 트랜잭션 매니저가 전술한 예외를 던짐
         * 결국 전체 트랜잭션 롤백
         */
        Assertions.assertThrows(UnexpectedRollbackException.class, () -> manager.commit(outer));
    }

    @DisplayName("REQUIRES_NEW : 외부 트랜잭션과 내부 트랜잭션 완전 분리")
    @Test
    void transactionPropagationRequiresNew() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = manager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
        attribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        /**
         * 기존 트랜잭션에 참여하지 않고 새로운 트랜잭션 생성
         * 현재 실행 중인 외부 트랜잭션은 일시 강제 중단(트랜잭션 컨텍스트에서 제거)됐다가 다시 재개
         */
        TransactionStatus inner = manager.getTransaction(attribute);
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());

        log.info("내부 트랜잭션 롤백");
        manager.rollback(inner);

        log.info("외부 트랜잭션 커밋");
        manager.commit(outer);
    }
}

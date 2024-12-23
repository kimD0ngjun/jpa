package com.example.nPlusOne.persistenceContext.transaction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
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
}

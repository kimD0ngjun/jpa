package com.example.persistenceContext.transaction;

import com.example.persistenceContext.idea.service.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomAopTest {

    @Autowired
    private MessageService messageService;

    @DisplayName("AOP 동작 디버깅 테스트")
    @Test
    void debug() {

    }
}

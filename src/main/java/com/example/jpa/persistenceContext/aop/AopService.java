package com.example.jpa.persistenceContext.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AopService {
    public void find() {
        log.info("서비스 조회 로직");
    }

    public void save() {
        log.info("서비스 저장 로직");
    }
}

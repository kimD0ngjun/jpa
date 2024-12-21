package com.example.persistenceContext.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * AOP 어드바이스 : 특정 메소드 실행 전후에 삽입될 행동 정의(여기서는 실행시간 게측)
 */
@Slf4j
@Component
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice.invoke() 실행");
        long startMs = System.currentTimeMillis();

        Object target = invocation.proceed();

        long endMs = System.currentTimeMillis();
        log.info("TimeAdvice.invoke() 종료, 실행 시간 {} ms", endMs - startMs);
        return target;
    }
}

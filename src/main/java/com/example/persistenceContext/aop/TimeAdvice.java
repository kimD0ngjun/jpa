package com.example.persistenceContext.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * AOP 어드바이스 : 특정 메소드 실행 전후에 삽입될 행동 정의
 * (여기서는 실행 시간을 계측해서 로깅을 남김)
 * MethodInterceptor 인터셉터 구현 방식으로 실행 전후의 어드바이스 구현, 전통적인 스타일
 */
@Slf4j
@Component
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice.invoke() 실행");
        long startMs = System.currentTimeMillis(); // 메소드 실행 전, 실행시간 기록

        Object target = invocation.proceed(); // 메소드 실행

        long endMs = System.currentTimeMillis();
        log.info("TimeAdvice.invoke() 종료, 실행 시간 {} ms", endMs - startMs); // 메소드 실행 후, 실행시간 기록

        return target; // 메소드 실행 결과 반환
    }
}

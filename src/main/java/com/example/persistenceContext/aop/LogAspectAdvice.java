package com.example.persistenceContext.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP 어드바이스 : 특정 메소드 실행 전후에 삽입될 행동 정의
 * (여기서는 실행 전에 메소드 서명을 로깅하고 실행 후에는 실행 결과 로깅)
 * @Around 어노테이션 기반 구현, AspectJ 스타일
 * 서비스의 find() 메소드 실행 전후의 AOP 세팅
 */
@Slf4j
@Aspect
@Component
public class LogAspectAdvice {
    // execution(<접근 제어자> <반환 타입> <클래스 경로>.<메소드 이름>(<매개변수 목록>))
    // Advice 적용 대상 메소드를 정의하는 포인트컷 표현식
    @Around("execution(void com.example.persistenceContext.aop.AopService.find(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        String message = joinPoint.getSignature().toShortString(); // 메소드 서명(이름, 매개변수 등) 갖고오기
        log.info("실행 메소드를 찾기 위한 ProceedingJoinPoint 객체 메소드 기반 조회");

        Object result = joinPoint.proceed(); // 메소드 실행

        log.info("실행 메소드 정체: {}", message); // 실행된 메소드의 결과 출력

        return result; // 메소드 실행 결과 반환
    }
}

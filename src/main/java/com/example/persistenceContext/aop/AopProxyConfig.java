package com.example.persistenceContext.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 메소드인터셉터 기반 AOP 어드비아시 설정 시, 수동 포인트컷 지정 필요
 * (설정 클래스로 처리)
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AopProxyConfig {

    private final TimeAdvice timeAdvice;

    @Bean
    public Advisor advisor1() {
        // 수동 포인트컷 작성
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(void com.example.persistenceContext.aop.AopService.save(..))");

        return new DefaultPointcutAdvisor(pointcut, timeAdvice);
    }

}

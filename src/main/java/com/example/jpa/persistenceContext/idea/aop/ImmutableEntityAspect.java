package com.example.jpa.persistenceContext.idea.aop;

import com.example.jpa.persistenceContext.idea.annotation.ImmutableEntity;
import com.example.jpa.persistenceContext.idea.cache.ImmutableEntityCache;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@RequiredArgsConstructor
public class ImmutableEntityAspect {

    private final ImmutableEntityCache cache;

    // 영속성 컨텍스트 생성조차 캐싱으로 건너뛰게 할 순 없으려나
    @Around("@annotation(transactional) && execution(* *(..))")
    public Object aroundTransactionalMethod(ProceedingJoinPoint joinPoint, Transactional transactional) throws Throwable {
        // 호출 대상 메서드의 클래스 및 리턴 타입 확인
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> returnType = signature.getReturnType();

        // 불변 엔티티인지 확인
        if (!returnType.isAnnotationPresent(ImmutableEntity.class)) {
            return joinPoint.proceed();
        }

        // 캐시 확인 (예: 첫 번째 파라미터를 ID로 가정)
        Object[] args = joinPoint.getArgs();
        Long id = (Long) args[0];

        // 캐시가 존재하면 캐시된 값을 반환
        if (cache.contains(id)) {
            return cache.get(id, returnType);
        }

        // 캐시 미스 시 원래 로직 수행 (영속성 컨텍스트 없이 DB에서 조회하지 않음)
        Object result = joinPoint.proceed();

        // 결과가 있으면 캐시 저장
        if (result != null) {
            cache.put(id, result);
        }

        return result;
    }
}
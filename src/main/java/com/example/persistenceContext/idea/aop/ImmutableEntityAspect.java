package com.example.persistenceContext.idea.aop;

import com.example.persistenceContext.idea.annotation.ImmutableEntity;
import com.example.persistenceContext.idea.cache.ImmutableEntityCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class ImmutableEntityAspect {

    private final ImmutableEntityCache cache;

    public ImmutableEntityAspect(ImmutableEntityCache cache) {
        this.cache = cache;
    }

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

        if (cache.contains(id)) {
            return cache.get(id, returnType);
        }

        // 캐시 미스 시 원래 로직 수행
        Object result = joinPoint.proceed();

        // 조회 결과 캐싱
        cache.put(id, result);
        return result;
    }
}

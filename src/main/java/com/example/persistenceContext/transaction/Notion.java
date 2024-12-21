package com.example.persistenceContext.transaction;

import jakarta.transaction.TransactionManager;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Notion {

    CrudRepository crudRepository;

    TransactionManager transactionManager;

    /**
     * @Aspect 어노테이션이 붙은 클래스를 Advisor 로 변환해서 저장한다.
     * 스프링에 등록된 'Advisor빈'들을 자동으로 찾아서 프록시가 필요한 곳에 프록시를 적용해준다.
     */
    AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator;

}

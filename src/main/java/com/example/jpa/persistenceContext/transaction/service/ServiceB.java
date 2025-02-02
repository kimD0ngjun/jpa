package com.example.jpa.persistenceContext.transaction.service;

import com.example.jpa.persistenceContext.transaction.entity.Human;
import com.example.jpa.persistenceContext.transaction.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceB {

    private final HumanRepository humanRepository;

    public Human findById(Long id) {
        return humanRepository.findById(id).orElseThrow(
                IllegalArgumentException::new
        );
    }
}

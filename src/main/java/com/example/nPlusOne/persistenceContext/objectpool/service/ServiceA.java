package com.example.nPlusOne.persistenceContext.objectpool.service;

import com.example.nPlusOne.persistenceContext.objectpool.entity.Human;
import com.example.nPlusOne.persistenceContext.objectpool.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceA {

    private final HumanRepository humanRepository;

    public Human findById(Long id) {
        return humanRepository.findById(id).orElseThrow(
                IllegalArgumentException::new
        );
    }
}

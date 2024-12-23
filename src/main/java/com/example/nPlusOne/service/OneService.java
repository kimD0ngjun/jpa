package com.example.nPlusOne.service;

import com.example.nPlusOne.repository.OneEntityGraphRepository;
import com.example.nPlusOne.repository.OneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class OneService {

    private final OneRepository oneRepository;
    private final OneEntityGraphRepository oneEntityGraphRepository;

    public void findAllNumbers() {
        List<String> list = oneRepository.findAll().stream()
                .flatMap(one -> one.getTwoList().stream()
                        .flatMap(two -> two.getThreeList().stream()
                                .map(three -> one.getName() + two.getName() + three.getName())))
                .toList();

        System.out.println("결과: " + list);
    }

    public void findALlNumbersByFetchJoin() {
        List<String> list = oneRepository.findAllWithTwoAndThree().stream()
                .flatMap(one -> one.getTwoList().stream()
                        .flatMap(two -> two.getThreeList().stream()
                                .map(three -> one.getName() + two.getName() + three.getName())))
                .toList();

        System.out.println("결과: " + list);
    }

    public void findNumbersByEntityGraph() {
        List<String> list = oneEntityGraphRepository.findAllWithEntityGraph().stream()
                .flatMap(one -> one.getTwoList().stream()
                        .flatMap(two -> two.getThreeList().stream()
                                .map(three -> one.getName() + two.getName() + three.getName())))
                .toList();

        System.out.println("결과: " + list);
    }
}

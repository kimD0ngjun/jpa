package com.example.nPlusOne.service;

import com.example.nPlusOne.repository.ARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Transactional
@Service
@RequiredArgsConstructor
public class AService {

    private final ARepository aRepository;

    public void findAllBC() {
        List<String> list = aRepository.findAll().stream()
                .flatMap(a -> {
                    Stream<String> bStream = a.getBList()
                            .stream().map(b -> a.getName() + b.getName());
                    Stream<String> cStream = a.getCList()
                            .stream().map(c -> a.getName() + c.getName());
                    return Stream.concat(bStream, cStream);
                }).toList();

        System.out.println("결과: " + list);
    }

    /**
     * hibernate의 중복 호출을 방지하기 위해 예외가 발생
     */
    public void findAllBCWithFetchJoin() {
        List<String> list = aRepository.findAllWithBAndC().stream()
                .flatMap(a -> {
                    Stream<String> bStream = a.getBList()
                            .stream().map(b -> a.getName() + b.getName());
                    Stream<String> cStream = a.getCList()
                            .stream().map(c -> a.getName() + c.getName());
                    return Stream.concat(bStream, cStream);
                }).toList();

        System.out.println("결과: " + list);
    }

    public void findAllBCWithEntityGraph() {
        List<String> list = aRepository.findAll().stream()
                .flatMap(a -> {
                    Stream<String> bStream = a.getBList()
                            .stream().map(b -> a.getName() + b.getName());
                    Stream<String> cStream = a.getCList()
                            .stream().map(c -> a.getName() + c.getName());
                    return Stream.concat(bStream, cStream);
                }).toList();

        System.out.println("결과: " + list);
    }
}

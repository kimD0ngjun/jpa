package com.example.jpa.nPlusOne.service;

import com.example.jpa.nPlusOne.repository.ARepository;
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
}

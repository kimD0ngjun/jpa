package com.example.jpa.nPlusOne.service;

import com.example.jpa.nPlusOne.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EntityGraphService {

    private final TeamRepository teamRepository;


    public void findAllMembersWithEntityGraph() {
        List<String> list = teamRepository.findAll().stream()
                .flatMap(team -> team.getMembers().stream()
                        .map(member -> team.getName() + ": " + member.getName()))
                .toList();

        System.out.println("결과: " + list);;
    }

}

package com.example.jpa.nPlusOne.service;

import com.example.jpa.nPlusOne.entity.Member;
import com.example.jpa.nPlusOne.entity.Team;
import com.example.jpa.nPlusOne.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TeamMemberService {
    private final TeamRepository teamRepository;

    /**
     * one query(select team from team) + N query(select member from member where~)
     */
    public void findAllMembers() {
        List<String> list = teamRepository.findAll().stream()
                .flatMap(team -> team.getMembers().stream()
                        .map(member -> team.getName() + ": " + member.getName()))
                .toList();

        System.out.println("결과: " + list);
    }

    public void findAllTeams() {
        teamRepository.findAll();
    }

    /**
     * one query(select team from team) + N query(select member from member where~)
     */
    public void findParticularMembers() {
        Team team = teamRepository.findById(1L).orElseThrow();

        List<String> list = team.getMembers().stream()
                .map(member -> team.getName() + ": " + member.getName())
                .toList();

        System.out.println("결과: " + list);
    }
}

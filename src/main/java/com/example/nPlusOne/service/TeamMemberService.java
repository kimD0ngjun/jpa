package com.example.nPlusOne.service;

import com.example.nPlusOne.entity.oneToN.Team;
import com.example.nPlusOne.repository.MemberRepository;
import com.example.nPlusOne.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TeamMemberService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    /**
     * one query(select team from team) + N query(select member from member where~)
     *
     * @return
     */
    public List<String> findAllMembersByTeamRepo() {
        List<Team> teams = teamRepository.findAll();

        List<String> list = teams.stream()
                .flatMap(team -> team.getMembers().stream()
                        .map(member -> team.getName() + ": " + member.getName()))
                .toList();

        System.out.println("결과: " + list);
        return list;
    }

    public void findAllMembersByMemberRepo() {
        List<String> list = memberRepository.findAll().stream()
                .map(member -> member.getTeam().getName() + ": " + member.getName())
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

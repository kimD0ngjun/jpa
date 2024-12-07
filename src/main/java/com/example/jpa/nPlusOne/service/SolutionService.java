package com.example.jpa.nPlusOne.service;

import com.example.jpa.nPlusOne.entity.Team;
import com.example.jpa.nPlusOne.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class SolutionService {

    private final TeamRepository teamRepository;

    /**
     * Fetch Join
     * select t.team_id, t.team_name, m.member_id, m.member_name
     * from team t left join member m on t.team_id = m.team_id;
     */
    public void findAllMembersByFetchJoin() {
        List<String> list = teamRepository.findAllWithMembers().stream()
                .flatMap(team -> team.getMembers().stream()
                        .map(member -> team.getName() + ": " + member.getName()))
                .toList();

        System.out.println("결과: " + list);
    }
}

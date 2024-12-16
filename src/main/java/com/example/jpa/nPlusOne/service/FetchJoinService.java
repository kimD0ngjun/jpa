package com.example.jpa.nPlusOne.service;

import com.example.jpa.nPlusOne.entity.oneToN.Team;
import com.example.jpa.nPlusOne.repository.TeamJdbcRepository;
import com.example.jpa.nPlusOne.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class FetchJoinService {

    private final TeamRepository teamRepository;
    private final TeamJdbcRepository teamJdbcRepository;

    /**
     * Fetch Join
     * select t.team_id, t.team_name, m.member_id, m.member_name
     * from team t left join member m on t.team_id = m.team_id;
     *
     * @return
     */
    public List<String> findAllMembersByFetchJoin() {
        List<String> list = teamRepository.findAllWithMembers().stream()
                .flatMap(team -> team.getMembers().stream()
                        .map(member -> team.getName() + ": " + member.getName()))
                .toList();

        System.out.println("결과: " + list);
        return list;
    }

    /**
     * JDBC like JPQL Fetch Join
     */
    public void findAllMembersByJdbc() {
        List<String> list = teamJdbcRepository.findAll();
        System.out.println(list);
    }

    /**
     * Don't use Paging with Fetch Join
     */
    public void findAllMembersWithPaging() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.asc("name")));
        Page<Team> page = teamRepository.findAllWithMembers(pageable);
        List<String> list = page.getContent().stream()
                .flatMap(team -> team.getMembers().stream()
                        .map(member -> team.getName() + ": " + member.getName()))
                .toList();

        System.out.println("결과: " + list);;
    }
}

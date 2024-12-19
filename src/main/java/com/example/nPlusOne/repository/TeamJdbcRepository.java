package com.example.nPlusOne.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class TeamJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<String> findAll() {
        String sql = "SELECT t.team_id, t.team_name, m.member_id, m.member_name " +
                "FROM team t " +
                "LEFT JOIN member m ON t.team_id = m.team_id";

        // 팀 ID로 그룹핑하여 Map에 저장
        List<String> list = new ArrayList<>();

        jdbcTemplate.query(sql, rs -> {
            long teamId = rs.getLong("team_id");
            String teamName = rs.getString("team_name");
            long memberId = rs.getLong("member_id");
            String memberName = rs.getString("member_name");

            // Member가 존재하면 Team에 추가
            list.add(teamName + " :" + memberName);
        });

        return list;
    }
}

package com.example.persistenceContext.transaction.service;

import com.example.persistenceContext.transaction.entity.Human;
import com.example.persistenceContext.transaction.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final HumanRepository humanRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public String updateWithTransaction(Long id){
        return getString(id);
    }

    public String updateWithoutTransaction(Long id){
        return getString(id);
    }

    public String check() {
        String sql = "select h.name from human h where h.id = 2";
        String check = jdbcTemplate.queryForObject(sql, String.class);

        return "\n* 실제 결과 : " + check;
    }

    private String getString(Long id) {
        Human human = humanRepository.findById(id).orElseThrow(
                IllegalArgumentException::new);
        String previous = "- 이전 결과 : " + human.getName();

        UUID uuid = UUID.randomUUID();
        human.setName(uuid.toString());
        String post = "- 업데이트 결과 : " + human.getName();

        return previous + "\n" + post;
    }
}

package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.dto.AuthorWithOrganizationDTO;
import com.example.jpa.queryDsl.repository.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectionController {

    private final AuthorRepository authorRepository;

    @GetMapping("/projection1")
    public List<AuthorWithOrganizationDTO> projectionJoin() {
        return authorRepository.projectionJoin();
    }
}

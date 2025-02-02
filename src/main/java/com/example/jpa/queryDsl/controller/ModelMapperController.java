package com.example.jpa.queryDsl.controller;

import com.example.jpa.queryDsl.dto.AuthorDTO;
import com.example.jpa.queryDsl.repository.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModelMapperController {

    private final AuthorRepository authorRepository;

    @GetMapping("/model1")
    public List<AuthorDTO> modelMapperJoin() {
        return authorRepository.modelMapperJoin();
    }
}

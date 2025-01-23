package com.example.jpa.queryDsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private List<BookDTO> books = new ArrayList<>();
}

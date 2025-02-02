package com.example.jpa.queryDsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorWithOrganizationDTO {

    private Long id;

    private String name;

    private int age;

    private String gender;

    private OrganizationDTO organization;

    @QueryProjection
    public AuthorWithOrganizationDTO(
            Long id, String name, int age, String gender, OrganizationDTO organization) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.organization = organization;
    }
}

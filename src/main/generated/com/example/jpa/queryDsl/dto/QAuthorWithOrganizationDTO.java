package com.example.jpa.queryDsl.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.jpa.queryDsl.dto.QAuthorWithOrganizationDTO is a Querydsl Projection type for AuthorWithOrganizationDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAuthorWithOrganizationDTO extends ConstructorExpression<AuthorWithOrganizationDTO> {

    private static final long serialVersionUID = 451794103L;

    public QAuthorWithOrganizationDTO(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> age, com.querydsl.core.types.Expression<String> gender, com.querydsl.core.types.Expression<? extends OrganizationDTO> organization) {
        super(AuthorWithOrganizationDTO.class, new Class<?>[]{long.class, String.class, int.class, String.class, OrganizationDTO.class}, id, name, age, gender, organization);
    }

}


package com.example.jpa.queryDsl.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.jpa.queryDsl.dto.QOrganizationDTO is a Querydsl Projection type for OrganizationDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrganizationDTO extends ConstructorExpression<OrganizationDTO> {

    private static final long serialVersionUID = 2130911464L;

    public QOrganizationDTO(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> orgName) {
        super(OrganizationDTO.class, new Class<?>[]{long.class, String.class}, id, orgName);
    }

}


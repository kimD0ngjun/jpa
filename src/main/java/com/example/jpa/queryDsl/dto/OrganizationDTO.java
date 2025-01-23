package com.example.jpa.queryDsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationDTO {
    private Long id;
    private String orgName;

    @QueryProjection
    public OrganizationDTO(Long id, String orgName) {
        this.id = id;
        this.orgName = orgName;
    }
}

package com.example.jpa.queryDsl.repository.organization;

import com.example.jpa.queryDsl.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository
        extends JpaRepository<Organization, Long>, CustomOrganizationRepository {
}

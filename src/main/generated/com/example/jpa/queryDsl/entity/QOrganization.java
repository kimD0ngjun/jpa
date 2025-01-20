package com.example.jpa.queryDsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrganization is a Querydsl query type for Organization
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrganization extends EntityPathBase<Organization> {

    private static final long serialVersionUID = -764761879L;

    public static final QOrganization organization = new QOrganization("organization");

    public final ListPath<Author, QAuthor> authors = this.<Author, QAuthor>createList("authors", Author.class, QAuthor.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orgName = createString("orgName");

    public QOrganization(String variable) {
        super(Organization.class, forVariable(variable));
    }

    public QOrganization(Path<? extends Organization> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrganization(PathMetadata metadata) {
        super(Organization.class, metadata);
    }

}


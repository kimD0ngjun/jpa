package com.example.jpa.persistenceContext.transaction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHuman is a Querydsl query type for Human
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHuman extends EntityPathBase<Human> {

    private static final long serialVersionUID = -2135072020L;

    public static final QHuman human = new QHuman("human");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QHuman(String variable) {
        super(Human.class, forVariable(variable));
    }

    public QHuman(Path<? extends Human> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHuman(PathMetadata metadata) {
        super(Human.class, metadata);
    }

}


package com.example.jpa.nPlusOne.entity.multiOneToN;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QB is a Querydsl query type for B
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QB extends EntityPathBase<B> {

    private static final long serialVersionUID = -1908072357L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QB b = new QB("b");

    public final QA a;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QB(String variable) {
        this(B.class, forVariable(variable), INITS);
    }

    public QB(Path<? extends B> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QB(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QB(PathMetadata metadata, PathInits inits) {
        this(B.class, metadata, inits);
    }

    public QB(Class<? extends B> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.a = inits.isInitialized("a") ? new QA(forProperty("a")) : null;
    }

}


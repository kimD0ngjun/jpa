package com.example.jpa.queryDsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSelf is a Querydsl query type for Self
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSelf extends EntityPathBase<Self> {

    private static final long serialVersionUID = -1810521854L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSelf self1 = new QSelf("self1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QSelf self;

    public QSelf(String variable) {
        this(Self.class, forVariable(variable), INITS);
    }

    public QSelf(Path<? extends Self> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSelf(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSelf(PathMetadata metadata, PathInits inits) {
        this(Self.class, metadata, inits);
    }

    public QSelf(Class<? extends Self> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.self = inits.isInitialized("self") ? new QSelf(forProperty("self"), inits.get("self")) : null;
    }

}


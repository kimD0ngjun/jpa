package com.example.jpa.nPlusOne.entity.multiOneToN;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QC is a Querydsl query type for C
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QC extends EntityPathBase<C> {

    private static final long serialVersionUID = -1908072356L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QC c = new QC("c");

    public final QA a;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QC(String variable) {
        this(C.class, forVariable(variable), INITS);
    }

    public QC(Path<? extends C> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QC(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QC(PathMetadata metadata, PathInits inits) {
        this(C.class, metadata, inits);
    }

    public QC(Class<? extends C> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.a = inits.isInitialized("a") ? new QA(forProperty("a")) : null;
    }

}


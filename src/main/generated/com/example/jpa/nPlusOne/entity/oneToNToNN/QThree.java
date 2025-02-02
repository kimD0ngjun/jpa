package com.example.jpa.nPlusOne.entity.oneToNToNN;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThree is a Querydsl query type for Three
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThree extends EntityPathBase<Three> {

    private static final long serialVersionUID = -1910932489L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QThree three = new QThree("three");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTwo two;

    public QThree(String variable) {
        this(Three.class, forVariable(variable), INITS);
    }

    public QThree(Path<? extends Three> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QThree(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QThree(PathMetadata metadata, PathInits inits) {
        this(Three.class, metadata, inits);
    }

    public QThree(Class<? extends Three> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.two = inits.isInitialized("two") ? new QTwo(forProperty("two"), inits.get("two")) : null;
    }

}


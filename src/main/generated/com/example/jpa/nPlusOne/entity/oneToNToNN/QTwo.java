package com.example.jpa.nPlusOne.entity.oneToNToNN;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTwo is a Querydsl query type for Two
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTwo extends EntityPathBase<Two> {

    private static final long serialVersionUID = 1133206245L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTwo two = new QTwo("two");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QOne one;

    public final ListPath<Three, QThree> threeList = this.<Three, QThree>createList("threeList", Three.class, QThree.class, PathInits.DIRECT2);

    public QTwo(String variable) {
        this(Two.class, forVariable(variable), INITS);
    }

    public QTwo(Path<? extends Two> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTwo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTwo(PathMetadata metadata, PathInits inits) {
        this(Two.class, metadata, inits);
    }

    public QTwo(Class<? extends Two> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.one = inits.isInitialized("one") ? new QOne(forProperty("one")) : null;
    }

}


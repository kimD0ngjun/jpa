package com.example.jpa.nPlusOne.entity.multiOneToN;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QA is a Querydsl query type for A
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QA extends EntityPathBase<A> {

    private static final long serialVersionUID = -1908072358L;

    public static final QA a = new QA("a");

    public final ListPath<B, QB> bList = this.<B, QB>createList("bList", B.class, QB.class, PathInits.DIRECT2);

    public final ListPath<C, QC> cList = this.<C, QC>createList("cList", C.class, QC.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QA(String variable) {
        super(A.class, forVariable(variable));
    }

    public QA(Path<? extends A> path) {
        super(path.getType(), path.getMetadata());
    }

    public QA(PathMetadata metadata) {
        super(A.class, metadata);
    }

}


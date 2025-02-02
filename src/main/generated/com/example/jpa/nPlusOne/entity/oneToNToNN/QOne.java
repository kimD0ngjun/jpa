package com.example.jpa.nPlusOne.entity.oneToNToNN;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOne is a Querydsl query type for One
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOne extends EntityPathBase<One> {

    private static final long serialVersionUID = 1133201151L;

    public static final QOne one = new QOne("one");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Two, QTwo> twoList = this.<Two, QTwo>createList("twoList", Two.class, QTwo.class, PathInits.DIRECT2);

    public QOne(String variable) {
        super(One.class, forVariable(variable));
    }

    public QOne(Path<? extends One> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOne(PathMetadata metadata) {
        super(One.class, metadata);
    }

}


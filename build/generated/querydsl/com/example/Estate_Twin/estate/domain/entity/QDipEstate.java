package com.example.Estate_Twin.estate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDipEstate is a Querydsl query type for DipEstate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDipEstate extends EntityPathBase<DipEstate> {

    private static final long serialVersionUID = 829400952L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDipEstate dipEstate = new QDipEstate("dipEstate");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QEstate estate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.example.Estate_Twin.user.domain.entity.QUser user;

    public QDipEstate(String variable) {
        this(DipEstate.class, forVariable(variable), INITS);
    }

    public QDipEstate(Path<? extends DipEstate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDipEstate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDipEstate(PathMetadata metadata, PathInits inits) {
        this(DipEstate.class, metadata, inits);
    }

    public QDipEstate(Class<? extends DipEstate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new QEstate(forProperty("estate"), inits.get("estate")) : null;
        this.user = inits.isInitialized("user") ? new com.example.Estate_Twin.user.domain.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


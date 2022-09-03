package com.example.Estate_Twin.estate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDipRecentEstate is a Querydsl query type for DipRecentEstate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDipRecentEstate extends EntityPathBase<DipRecentEstate> {

    private static final long serialVersionUID = -1661560653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDipRecentEstate dipRecentEstate = new QDipRecentEstate("dipRecentEstate");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath flag = createBoolean("flag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.example.Estate_Twin.user.domain.entity.QUser tanent;

    public QDipRecentEstate(String variable) {
        this(DipRecentEstate.class, forVariable(variable), INITS);
    }

    public QDipRecentEstate(Path<? extends DipRecentEstate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDipRecentEstate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDipRecentEstate(PathMetadata metadata, PathInits inits) {
        this(DipRecentEstate.class, metadata, inits);
    }

    public QDipRecentEstate(Class<? extends DipRecentEstate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tanent = inits.isInitialized("tanent") ? new com.example.Estate_Twin.user.domain.entity.QUser(forProperty("tanent"), inits.get("tanent")) : null;
    }

}


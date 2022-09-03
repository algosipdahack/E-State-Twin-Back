package com.example.Estate_Twin.estate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEstateHit is a Querydsl query type for EstateHit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEstateHit extends EntityPathBase<EstateHit> {

    private static final long serialVersionUID = -672703384L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEstateHit estateHit = new QEstateHit("estateHit");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QEstate estate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> totalHit = createNumber("totalHit", Long.class);

    public final NumberPath<Long> weeklyHit = createNumber("weeklyHit", Long.class);

    public QEstateHit(String variable) {
        this(EstateHit.class, forVariable(variable), INITS);
    }

    public QEstateHit(Path<? extends EstateHit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEstateHit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEstateHit(PathMetadata metadata, PathInits inits) {
        this(EstateHit.class, metadata, inits);
    }

    public QEstateHit(Class<? extends EstateHit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new QEstate(forProperty("estate"), inits.get("estate")) : null;
    }

}


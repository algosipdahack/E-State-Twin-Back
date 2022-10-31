package com.example.Estate_Twin.estate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPreferEstate is a Querydsl query type for PreferEstate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPreferEstate extends EntityPathBase<PreferEstate> {

    private static final long serialVersionUID = -563111397L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPreferEstate preferEstate = new QPreferEstate("preferEstate");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QEstate estate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<Preference> preference = createEnum("preference", Preference.class);

    public final com.example.Estate_Twin.user.domain.entity.QUser user;

    public QPreferEstate(String variable) {
        this(PreferEstate.class, forVariable(variable), INITS);
    }

    public QPreferEstate(Path<? extends PreferEstate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPreferEstate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPreferEstate(PathMetadata metadata, PathInits inits) {
        this(PreferEstate.class, metadata, inits);
    }

    public QPreferEstate(Class<? extends PreferEstate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new QEstate(forProperty("estate"), inits.get("estate")) : null;
        this.user = inits.isInitialized("user") ? new com.example.Estate_Twin.user.domain.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


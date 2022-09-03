package com.example.Estate_Twin.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 343123851L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> birthday = _super.birthday;

    public final com.example.Estate_Twin.address.data.entity.QAddress broker_address;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<com.example.Estate_Twin.estate.domain.entity.DipRecentEstate, com.example.Estate_Twin.estate.domain.entity.QDipRecentEstate> dipRecentEstates = this.<com.example.Estate_Twin.estate.domain.entity.DipRecentEstate, com.example.Estate_Twin.estate.domain.entity.QDipRecentEstate>createList("dipRecentEstates", com.example.Estate_Twin.estate.domain.entity.DipRecentEstate.class, com.example.Estate_Twin.estate.domain.entity.QDipRecentEstate.class, PathInits.DIRECT2);

    //inherited
    public final StringPath email = _super.email;

    public final ListPath<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate> estates = this.<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate>createList("estates", com.example.Estate_Twin.estate.domain.entity.Estate.class, com.example.Estate_Twin.estate.domain.entity.QEstate.class, PathInits.DIRECT2);

    public final StringPath estateType = createString("estateType");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath phone = _super.phone;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.broker_address = inits.isInitialized("broker_address") ? new com.example.Estate_Twin.address.data.entity.QAddress(forProperty("broker_address"), inits.get("broker_address")) : null;
    }

}


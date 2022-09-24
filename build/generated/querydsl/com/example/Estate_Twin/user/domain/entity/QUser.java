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

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    public final EnumPath<AuthProvider> authProvider = createEnum("authProvider", AuthProvider.class);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    public final StringPath borough = createString("borough");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final SetPath<com.example.Estate_Twin.estate.domain.entity.DipEstate, com.example.Estate_Twin.estate.domain.entity.QDipEstate> dipEstates = this.<com.example.Estate_Twin.estate.domain.entity.DipEstate, com.example.Estate_Twin.estate.domain.entity.QDipEstate>createSet("dipEstates", com.example.Estate_Twin.estate.domain.entity.DipEstate.class, com.example.Estate_Twin.estate.domain.entity.QDipEstate.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final EnumPath<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType = createEnum("estateType", com.example.Estate_Twin.estate.domain.entity.EstateType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isBroker = createBoolean("isBroker");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final SetPath<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate> ownEstates = this.<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate>createSet("ownEstates", com.example.Estate_Twin.estate.domain.entity.Estate.class, com.example.Estate_Twin.estate.domain.entity.QEstate.class, PathInits.DIRECT2);

    public final StringPath phone = createString("phone");

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final com.example.Estate_Twin.estate.domain.entity.QEstate tanentEstate;

    public final EnumPath<com.example.Estate_Twin.estate.domain.entity.TransactionType> transactionType = createEnum("transactionType", com.example.Estate_Twin.estate.domain.entity.TransactionType.class);

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
        this.tanentEstate = inits.isInitialized("tanentEstate") ? new com.example.Estate_Twin.estate.domain.entity.QEstate(forProperty("tanentEstate"), inits.get("tanentEstate")) : null;
    }

}


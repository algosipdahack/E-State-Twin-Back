package com.example.Estate_Twin.estate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEstate is a Querydsl query type for Estate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEstate extends EntityPathBase<Estate> {

    private static final long serialVersionUID = 1060203307L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEstate estate = new QEstate("estate");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    public final com.example.Estate_Twin.address.QAddress address;

    public final StringPath arCam = createString("arCam");

    public final SetPath<com.example.Estate_Twin.asset.data.entity.Asset, com.example.Estate_Twin.asset.data.entity.QAsset> assets = this.<com.example.Estate_Twin.asset.data.entity.Asset, com.example.Estate_Twin.asset.data.entity.QAsset>createSet("assets", com.example.Estate_Twin.asset.data.entity.Asset.class, com.example.Estate_Twin.asset.data.entity.QAsset.class, PathInits.DIRECT2);

    public final com.example.Estate_Twin.user.domain.entity.QBroker broker;

    public final BooleanPath brokerConfirmYN = createBoolean("brokerConfirmYN");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QEstateHit estateHit;

    public final ListPath<String, StringPath> estateMedia = this.<String, StringPath>createList("estateMedia", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath estateThumbNail = createString("estateThumbNail");

    public final EnumPath<Grade> grade = createEnum("grade", Grade.class);

    public final com.example.Estate_Twin.house.domain.entity.QHouse house;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPosted = createBoolean("isPosted");

    public final StringPath model = createString("model");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.example.Estate_Twin.user.domain.entity.QUser owner;

    public final BooleanPath ownerConfirmYN = createBoolean("ownerConfirmYN");

    public final SetPath<PreferEstate, QPreferEstate> preferEstates = this.<PreferEstate, QPreferEstate>createSet("preferEstates", PreferEstate.class, QPreferEstate.class, PathInits.DIRECT2);

    public final EnumPath<com.example.Estate_Twin.contractstate.domain.entity.State> state = createEnum("state", com.example.Estate_Twin.contractstate.domain.entity.State.class);

    public final com.example.Estate_Twin.user.domain.entity.QUser tenent;

    public final StringPath thumbnail3D = createString("thumbnail3D");

    public final EnumPath<TransactionType> transactionType = createEnum("transactionType", TransactionType.class);

    public QEstate(String variable) {
        this(Estate.class, forVariable(variable), INITS);
    }

    public QEstate(Path<? extends Estate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEstate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEstate(PathMetadata metadata, PathInits inits) {
        this(Estate.class, metadata, inits);
    }

    public QEstate(Class<? extends Estate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.example.Estate_Twin.address.QAddress(forProperty("address")) : null;
        this.broker = inits.isInitialized("broker") ? new com.example.Estate_Twin.user.domain.entity.QBroker(forProperty("broker"), inits.get("broker")) : null;
        this.estateHit = inits.isInitialized("estateHit") ? new QEstateHit(forProperty("estateHit"), inits.get("estateHit")) : null;
        this.house = inits.isInitialized("house") ? new com.example.Estate_Twin.house.domain.entity.QHouse(forProperty("house"), inits.get("house")) : null;
        this.owner = inits.isInitialized("owner") ? new com.example.Estate_Twin.user.domain.entity.QUser(forProperty("owner"), inits.get("owner")) : null;
        this.tenent = inits.isInitialized("tenent") ? new com.example.Estate_Twin.user.domain.entity.QUser(forProperty("tenent"), inits.get("tenent")) : null;
    }

}


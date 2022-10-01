package com.example.Estate_Twin.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBroker is a Querydsl query type for Broker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBroker extends EntityPathBase<Broker> {

    private static final long serialVersionUID = -1515043943L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBroker broker = new QBroker("broker");

    public final com.example.Estate_Twin.address.QAddress address;

    public final StringPath agentName = createString("agentName");

    public final StringPath brokerageRegistrationLicense = createString("brokerageRegistrationLicense");

    public final StringPath brokerageRegistrationNumber = createString("brokerageRegistrationNumber");

    public final StringPath brokerPhoto = createString("brokerPhoto");

    public final StringPath businessLicense = createString("businessLicense");

    public final StringPath businessName = createString("businessName");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final StringPath content = createString("content");

    public final NumberPath<Long> countOfTransactionCompletion = createNumber("countOfTransactionCompletion", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate> tradeEstates = this.<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate>createList("tradeEstates", com.example.Estate_Twin.estate.domain.entity.Estate.class, com.example.Estate_Twin.estate.domain.entity.QEstate.class, PathInits.DIRECT2);

    public final QUser user;

    public QBroker(String variable) {
        this(Broker.class, forVariable(variable), INITS);
    }

    public QBroker(Path<? extends Broker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBroker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBroker(PathMetadata metadata, PathInits inits) {
        this(Broker.class, metadata, inits);
    }

    public QBroker(Class<? extends Broker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.example.Estate_Twin.address.QAddress(forProperty("address")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}


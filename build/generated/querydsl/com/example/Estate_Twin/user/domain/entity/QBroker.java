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

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath agentName = createString("agentName");

    //inherited
    public final DateTimePath<java.util.Date> birthday = _super.birthday;

    public final com.example.Estate_Twin.address.data.entity.QAddress broker_address;

    public final StringPath brokerageRegistrationLicense = createString("brokerageRegistrationLicense");

    public final StringPath brokerageRegistrationNumber = createString("brokerageRegistrationNumber");

    public final StringPath brokerPhoto = createString("brokerPhoto");

    public final StringPath businessLicense = createString("businessLicense");

    public final StringPath businessName = createString("businessName");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath email = _super.email;

    public final ListPath<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate> estates = this.<com.example.Estate_Twin.estate.domain.entity.Estate, com.example.Estate_Twin.estate.domain.entity.QEstate>createList("estates", com.example.Estate_Twin.estate.domain.entity.Estate.class, com.example.Estate_Twin.estate.domain.entity.QEstate.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath phone = _super.phone;

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
        this.broker_address = inits.isInitialized("broker_address") ? new com.example.Estate_Twin.address.data.entity.QAddress(forProperty("broker_address"), inits.get("broker_address")) : null;
    }

}


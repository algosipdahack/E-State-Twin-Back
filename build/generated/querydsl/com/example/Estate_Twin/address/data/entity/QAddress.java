package com.example.Estate_Twin.address.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 1997887535L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final StringPath block = createString("block");

    public final StringPath borough = createString("borough");

    public final StringPath buildingName = createString("buildingName");

    public final StringPath city = createString("city");

    public final StringPath complexName = createString("complexName");

    public final com.example.Estate_Twin.estate.domain.entity.QEstate estate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> mainBuildingNumber = createNumber("mainBuildingNumber", Integer.class);

    public final StringPath roadName = createString("roadName");

    public final NumberPath<Integer> subBuildingNumber = createNumber("subBuildingNumber", Integer.class);

    public final StringPath town = createString("town");

    public final StringPath unit = createString("unit");

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddress(PathMetadata metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new com.example.Estate_Twin.estate.domain.entity.QEstate(forProperty("estate"), inits.get("estate")) : null;
    }

}


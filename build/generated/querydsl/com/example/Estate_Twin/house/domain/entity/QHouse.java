package com.example.Estate_Twin.house.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHouse is a Querydsl query type for House
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHouse extends EntityPathBase<House> {

    private static final long serialVersionUID = -1727668499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHouse house = new QHouse("house");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    public final NumberPath<Long> bathCount = createNumber("bathCount", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> currentFloors = createNumber("currentFloors", Long.class);

    public final NumberPath<Long> deposit = createNumber("deposit", Long.class);

    public final com.example.Estate_Twin.estate.domain.entity.QEstate estate;

    public final EnumPath<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType = createEnum("estateType", com.example.Estate_Twin.estate.domain.entity.EstateType.class);

    public final StringPath heatType = createString("heatType");

    public final NumberPath<Long> household = createNumber("household", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemsIncludedMaintenanceFee = createString("itemsIncludedMaintenanceFee");

    public final NumberPath<Long> maintenanceFee = createNumber("maintenanceFee", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> monthlyRent = createNumber("monthlyRent", Long.class);

    public final DateTimePath<java.time.LocalDateTime> moveInAvailableDate = createDateTime("moveInAvailableDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> netRentableArea = createNumber("netRentableArea", Long.class);

    public final BooleanPath parking = createBoolean("parking");

    public final NumberPath<Long> parkingFee = createNumber("parkingFee", Long.class);

    public final NumberPath<Long> rentableArea = createNumber("rentableArea", Long.class);

    public final NumberPath<Long> roomCount = createNumber("roomCount", Long.class);

    public final NumberPath<Long> sellingFee = createNumber("sellingFee", Long.class);

    public final BooleanPath shortTermRent = createBoolean("shortTermRent");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final NumberPath<Long> totalFloors = createNumber("totalFloors", Long.class);

    public final DateTimePath<java.time.LocalDateTime> usageAvailableDate = createDateTime("usageAvailableDate", java.time.LocalDateTime.class);

    public QHouse(String variable) {
        this(House.class, forVariable(variable), INITS);
    }

    public QHouse(Path<? extends House> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHouse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHouse(PathMetadata metadata, PathInits inits) {
        this(House.class, metadata, inits);
    }

    public QHouse(Class<? extends House> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new com.example.Estate_Twin.estate.domain.entity.QEstate(forProperty("estate"), inits.get("estate")) : null;
    }

}


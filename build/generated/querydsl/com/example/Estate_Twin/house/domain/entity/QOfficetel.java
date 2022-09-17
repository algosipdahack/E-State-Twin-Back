package com.example.Estate_Twin.house.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOfficetel is a Querydsl query type for Officetel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOfficetel extends EntityPathBase<Officetel> {

    private static final long serialVersionUID = 275554604L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOfficetel officetel = new QOfficetel("officetel");

    public final QHouse _super;

    //inherited
    public final NumberPath<Long> bathCount;

    public final BooleanPath builtIn = createBoolean("builtIn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    //inherited
    public final NumberPath<Long> currentFloors;

    //inherited
    public final NumberPath<Long> deposit;

    public final BooleanPath duplex = createBoolean("duplex");

    public final BooleanPath elevator = createBoolean("elevator");

    // inherited
    public final com.example.Estate_Twin.estate.domain.entity.QEstate estate;

    //inherited
    public final EnumPath<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType;

    //inherited
    public final StringPath heatType;

    //inherited
    public final NumberPath<Long> household;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath itemsIncludedMaintenanceFee;

    public final BooleanPath loft = createBoolean("loft");

    //inherited
    public final NumberPath<Long> maintenanceFee;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate;

    //inherited
    public final NumberPath<Long> monthlyRent;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> moveInAvailableDate;

    //inherited
    public final NumberPath<Long> netRentableArea;

    //inherited
    public final BooleanPath parking;

    //inherited
    public final NumberPath<Long> parkingFee;

    //inherited
    public final NumberPath<Long> rentableArea;

    //inherited
    public final NumberPath<Long> roomCount;

    //inherited
    public final NumberPath<Long> sellingFee;

    //inherited
    public final BooleanPath shortTermRent;

    //inherited
    public final NumberPath<Long> size;

    //inherited
    public final NumberPath<Long> totalFloors;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> usageAvailableDate;

    public final BooleanPath veranda = createBoolean("veranda");

    public QOfficetel(String variable) {
        this(Officetel.class, forVariable(variable), INITS);
    }

    public QOfficetel(Path<? extends Officetel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOfficetel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOfficetel(PathMetadata metadata, PathInits inits) {
        this(Officetel.class, metadata, inits);
    }

    public QOfficetel(Class<? extends Officetel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QHouse(type, metadata, inits);
        this.bathCount = _super.bathCount;
        this.createdDate = _super.createdDate;
        this.currentFloors = _super.currentFloors;
        this.deposit = _super.deposit;
        this.estate = _super.estate;
        this.estateType = _super.estateType;
        this.heatType = _super.heatType;
        this.household = _super.household;
        this.itemsIncludedMaintenanceFee = _super.itemsIncludedMaintenanceFee;
        this.maintenanceFee = _super.maintenanceFee;
        this.modifiedDate = _super.modifiedDate;
        this.monthlyRent = _super.monthlyRent;
        this.moveInAvailableDate = _super.moveInAvailableDate;
        this.netRentableArea = _super.netRentableArea;
        this.parking = _super.parking;
        this.parkingFee = _super.parkingFee;
        this.rentableArea = _super.rentableArea;
        this.roomCount = _super.roomCount;
        this.sellingFee = _super.sellingFee;
        this.shortTermRent = _super.shortTermRent;
        this.size = _super.size;
        this.totalFloors = _super.totalFloors;
        this.usageAvailableDate = _super.usageAvailableDate;
    }

}


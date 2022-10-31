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

    public final BooleanPath builtIn = createBoolean("builtIn");

    public final BooleanPath duplex = createBoolean("duplex");

    public final BooleanPath elevator = createBoolean("elevator");

    public final QHouse house;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath loft = createBoolean("loft");

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
        this.house = inits.isInitialized("house") ? new QHouse(forProperty("house"), inits.get("house")) : null;
    }

}


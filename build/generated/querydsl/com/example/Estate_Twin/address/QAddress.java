package com.example.Estate_Twin.address;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QAddress extends BeanPath<Address> {

    private static final long serialVersionUID = 1225702262L;

    public static final QAddress address = new QAddress("address");

    public final StringPath block = createString("block");

    public final StringPath borough = createString("borough");

    public final StringPath buildingName = createString("buildingName");

    public final StringPath city = createString("city");

    public final StringPath complexName = createString("complexName");

    public final NumberPath<Integer> mainBuildingNumber = createNumber("mainBuildingNumber", Integer.class);

    public final StringPath roadName = createString("roadName");

    public final NumberPath<Integer> subBuildingNumber = createNumber("subBuildingNumber", Integer.class);

    public final StringPath town = createString("town");

    public final StringPath unit = createString("unit");

    public QAddress(String variable) {
        super(Address.class, forVariable(variable));
    }

    public QAddress(Path<? extends Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddress(PathMetadata metadata) {
        super(Address.class, metadata);
    }

}


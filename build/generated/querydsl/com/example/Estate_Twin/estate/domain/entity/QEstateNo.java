package com.example.Estate_Twin.estate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEstateNo is a Querydsl query type for EstateNo
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEstateNo extends BeanPath<EstateNo> {

    private static final long serialVersionUID = 948131404L;

    public static final QEstateNo estateNo = new QEstateNo("estateNo");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public QEstateNo(String variable) {
        super(EstateNo.class, forVariable(variable));
    }

    public QEstateNo(Path<? extends EstateNo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEstateNo(PathMetadata metadata) {
        super(EstateNo.class, metadata);
    }

}


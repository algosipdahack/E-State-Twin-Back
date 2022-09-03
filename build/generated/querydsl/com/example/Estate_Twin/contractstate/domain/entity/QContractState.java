package com.example.Estate_Twin.contractstate.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContractState is a Querydsl query type for ContractState
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContractState extends EntityPathBase<ContractState> {

    private static final long serialVersionUID = 215904395L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContractState contractState = new QContractState("contractState");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final com.example.Estate_Twin.estate.domain.entity.QEstate estate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<State> state = createEnum("state", State.class);

    public QContractState(String variable) {
        this(ContractState.class, forVariable(variable), INITS);
    }

    public QContractState(Path<? extends ContractState> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContractState(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContractState(PathMetadata metadata, PathInits inits) {
        this(ContractState.class, metadata, inits);
    }

    public QContractState(Class<? extends ContractState> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new com.example.Estate_Twin.estate.domain.entity.QEstate(forProperty("estate"), inits.get("estate")) : null;
    }

}


package com.example.Estate_Twin.checklist.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCheckList is a Querydsl query type for CheckList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCheckList extends EntityPathBase<CheckList> {

    private static final long serialVersionUID = -1837061005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCheckList checkList = new QCheckList("checkList");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    public final com.example.Estate_Twin.asset.data.entity.QAsset asset;

    public final BooleanPath brokerConfirmYN = createBoolean("brokerConfirmYN");

    public final StringPath checkListContent = createString("checkListContent");

    public final StringPath checkListPhoto = createString("checkListPhoto");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath flawPart = createString("flawPart");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final BooleanPath ownerConfirmYN = createBoolean("ownerConfirmYN");

    public final DatePath<java.time.LocalDate> repairDate = createDate("repairDate", java.time.LocalDate.class);

    public final EnumPath<RepairType> repairType = createEnum("repairType", RepairType.class);

    public final BooleanPath tenantConfirmYN = createBoolean("tenantConfirmYN");

    public QCheckList(String variable) {
        this(CheckList.class, forVariable(variable), INITS);
    }

    public QCheckList(Path<? extends CheckList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCheckList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCheckList(PathMetadata metadata, PathInits inits) {
        this(CheckList.class, metadata, inits);
    }

    public QCheckList(Class<? extends CheckList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.asset = inits.isInitialized("asset") ? new com.example.Estate_Twin.asset.data.entity.QAsset(forProperty("asset"), inits.get("asset")) : null;
    }

}


package com.example.Estate_Twin.asset.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAsset is a Querydsl query type for Asset
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAsset extends EntityPathBase<Asset> {

    private static final long serialVersionUID = 726071527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAsset asset = new QAsset("asset");

    public final com.example.Estate_Twin.util.QBaseTimeEntity _super = new com.example.Estate_Twin.util.QBaseTimeEntity(this);

    public final StringPath anchorId = createString("anchorId");

    public final StringPath assetPhoto = createString("assetPhoto");

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final ListPath<com.example.Estate_Twin.checklist.data.entity.CheckList, com.example.Estate_Twin.checklist.data.entity.QCheckList> checkLists = this.<com.example.Estate_Twin.checklist.data.entity.CheckList, com.example.Estate_Twin.checklist.data.entity.QCheckList>createList("checkLists", com.example.Estate_Twin.checklist.data.entity.CheckList.class, com.example.Estate_Twin.checklist.data.entity.QCheckList.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final com.example.Estate_Twin.estate.domain.entity.QEstate estate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath manufacturer = createString("manufacturer");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath productName = createString("productName");

    public QAsset(String variable) {
        this(Asset.class, forVariable(variable), INITS);
    }

    public QAsset(Path<? extends Asset> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAsset(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAsset(PathMetadata metadata, PathInits inits) {
        this(Asset.class, metadata, inits);
    }

    public QAsset(Class<? extends Asset> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estate = inits.isInitialized("estate") ? new com.example.Estate_Twin.estate.domain.entity.QEstate(forProperty("estate"), inits.get("estate")) : null;
    }

}


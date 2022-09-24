package com.example.Estate_Twin.media.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedia is a Querydsl query type for Media
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMedia extends EntityPathBase<Media> {

    private static final long serialVersionUID = -180279691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedia media = new QMedia("media");

    public final com.example.Estate_Twin.asset.data.entity.QAsset asset;

    public final com.example.Estate_Twin.checklist.data.entity.QCheckList checkList;

    public final com.example.Estate_Twin.estate.domain.entity.QEstate estate;

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath origFileName = createString("origFileName");

    public QMedia(String variable) {
        this(Media.class, forVariable(variable), INITS);
    }

    public QMedia(Path<? extends Media> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMedia(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMedia(PathMetadata metadata, PathInits inits) {
        this(Media.class, metadata, inits);
    }

    public QMedia(Class<? extends Media> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.asset = inits.isInitialized("asset") ? new com.example.Estate_Twin.asset.data.entity.QAsset(forProperty("asset"), inits.get("asset")) : null;
        this.checkList = inits.isInitialized("checkList") ? new com.example.Estate_Twin.checklist.data.entity.QCheckList(forProperty("checkList"), inits.get("checkList")) : null;
        this.estate = inits.isInitialized("estate") ? new com.example.Estate_Twin.estate.domain.entity.QEstate(forProperty("estate"), inits.get("estate")) : null;
    }

}


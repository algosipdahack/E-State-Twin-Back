package com.example.Estate_Twin.asset.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.asset.web.dto.QAssetDto is a Querydsl Projection type for AssetDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QAssetDto extends ConstructorExpression<AssetDto> {

    private static final long serialVersionUID = -1319436134L;

    public QAssetDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.asset.data.entity.Asset> asset) {
        super(AssetDto.class, new Class<?>[]{com.example.Estate_Twin.asset.data.entity.Asset.class}, asset);
    }

}


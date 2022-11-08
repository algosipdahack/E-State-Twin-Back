package com.example.Estate_Twin.asset.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.asset.web.dto.QAssetSummaryDto is a Querydsl Projection type for AssetSummaryDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QAssetSummaryDto extends ConstructorExpression<AssetSummaryDto> {

    private static final long serialVersionUID = -426407618L;

    public QAssetSummaryDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.asset.data.entity.Asset> asset) {
        super(AssetSummaryDto.class, new Class<?>[]{com.example.Estate_Twin.asset.data.entity.Asset.class}, asset);
    }

}


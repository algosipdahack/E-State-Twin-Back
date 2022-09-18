package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.QAsset;
import com.example.Estate_Twin.asset.web.dto.AssetDto;
import com.example.Estate_Twin.checklist.data.entity.QCheckList;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.estate.web.dto.QEstateListResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetRepositoryCustomImpl extends QuerydslRepositorySupport implements AssetRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;
    private QAsset asset;
    private QCheckList checkList;
    public AssetRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Asset.class);
        this.asset = QAsset.asset;
        this.checkList = QCheckList.checkList;
    }
}

package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.QAssetResponseDto;
import com.example.Estate_Twin.checklist.data.entity.QCheckList;
import com.example.Estate_Twin.estate.domain.entity.QEstate;
import com.example.Estate_Twin.user.domain.entity.QUser;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetRepositoryCustomImpl extends QuerydslRepositorySupport implements AssetRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;
    private QEstate estate;
    private QAsset asset;
    private QUser user;
    private QCheckList checkList;

    public AssetRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Asset.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.estate = QEstate.estate;
        this.asset = QAsset.asset;
        this.user = QUser.user;
        this.checkList = QCheckList.checkList;
    }

    @Override
    public List<AssetResponseDto> findTenantAsset(Long userId, Category category) {
        QueryResults<AssetResponseDto> queryResults = jpaQueryFactory
                .select(new QAssetResponseDto(asset))
                .from(estate)
                .where(asset.category.eq(category))
                .where(estate.tenant.id.eq(userId))
                .join(estate.assets, asset)
                .join(asset.checkLists, checkList)
                .fetchResults();
        List<AssetResponseDto> result = queryResults.getResults();
        return result;
    }

    @Override
    public List<AssetResponseDto> findOwnerAsset(Long userId, Long estateId) {
        QueryResults<AssetResponseDto> queryResults = jpaQueryFactory
                .select(new QAssetResponseDto(asset))
                .from(estate)
                .where(estate.id.eq(estateId))
                .where(estate.owner.id.eq(userId))
                .join(estate.assets, asset)
                .join(asset.checkLists, checkList)
                .fetchResults();
        List<AssetResponseDto> result = queryResults.getResults();
        return result;
    }

}

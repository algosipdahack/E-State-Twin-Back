package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.*;
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

    public AssetRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Asset.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.estate = QEstate.estate;
        this.asset = QAsset.asset;
        this.user = QUser.user;
    }

    @Override
    public List<Asset> findTenantAsset(Long userId, Category category) {
        QueryResults<Asset> queryResults = jpaQueryFactory
                .select(asset)
                .from(estate)
                .where(asset.category.eq(category))
                .where(estate.tenant.id.eq(userId))
                .join(estate.assets, asset)
                .fetchResults();
        List<Asset> result = queryResults.getResults();
        return result;
    }

    @Override
    public List<Asset> findOwnerAsset(Long userId, Category category) {
        QueryResults<Asset> queryResults = jpaQueryFactory
                .select(asset)
                .from(estate)
                .where(asset.category.eq(category))
                .where(estate.owner.id.eq(userId))
                .join(estate.assets, asset)
                .fetchResults();
        List<Asset> result = queryResults.getResults();
        return result;
    }

}

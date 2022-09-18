package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.asset.data.entity.QAsset;
import com.example.Estate_Twin.asset.web.dto.AssetDto;
import com.example.Estate_Twin.asset.web.dto.QAssetDto;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.QCheckList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CheckListRepositoryCustomImpl extends QuerydslRepositorySupport implements CheckListRepositoryCustom {
    private QCheckList checkList;
    private QAsset asset;
    private JPAQueryFactory jpaQueryFactory;

    public CheckListRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(CheckList.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.asset = QAsset.asset;
        this.checkList = QCheckList.checkList;
    }

    @Override
    public AssetDto findAssetbyId(Long checkListId) {
         return jpaQueryFactory.select(new QAssetDto(asset))
                .from(asset)
                .leftJoin(checkList.asset, asset)
                .where(checkList.id.eq(checkListId))
                .fetchOne();
    }
}

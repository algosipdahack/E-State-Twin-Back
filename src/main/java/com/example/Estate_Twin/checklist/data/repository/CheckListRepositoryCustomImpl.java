package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.asset.data.entity.QAsset;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<CheckListResponseDto> findCheckListbyAssetId(Long assetId) {
        QueryResults<CheckListResponseDto> queryResults = jpaQueryFactory
                .select(new QCheckListResponseDto(checkList))
                .from(checkList)
                .where(checkList.asset.id.eq(assetId))
                .fetchResults();
        List<CheckListResponseDto> result = queryResults.getResults();
        return result;
    }
}

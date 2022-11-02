package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.asset.data.entity.QAsset;
import com.example.Estate_Twin.asset.web.dto.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstateRepositoryCustomImpl extends QuerydslRepositorySupport implements EstateRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;

    private QEstate estate;
    private QHouse house;
    private QEstateHit estateHit;
    private QAsset asset;
    public EstateRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Estate.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.estate = QEstate.estate;
        this.house = QHouse.house;
        this.estateHit = QEstateHit.estateHit;
        this.asset = QAsset.asset;
    }

    @Override
    public List<EstateMainDto> findByBoroughOrderByWeeklyHit(String borough) {
        QueryResults<EstateMainDto> queryResults = jpaQueryFactory
                .select(new QEstateMainDto(
                        estate.id,
                        estate.address.borough,
                        estate.estateThumbNail,
                        estate.address.town,
                        estate.thumbnail3D,
                        estate.transactionType,
                        estate.house.sellingFee,
                        estate.house.estateType
                ))
                .from(estate)
                .where(estate.address.borough.eq(borough))
                .where(estate.isPosted.eq(true))
                .orderBy(estate.estateHit.weeklyHit.desc())
                .limit(6) //6개씩 잘라서 주기
                .fetchResults();
        List<EstateMainDto> result = queryResults.getResults();
        return result;
    }

    private BooleanExpression ltEstateId(Long estateId) {
        if(estateId == null) {
            return null;
        }
        return estate.id.lt(estateId);
    }

    @Override
    public Page<EstateListResponseDto> findEstateList(Long estateId, Pageable pageable) {
        QueryResults<EstateListResponseDto> queryResults = jpaQueryFactory
                .select(new QEstateListResponseDto(
                        estate.id,
                        estate.transactionType,
                        estate.estateThumbNail,
                        estate.address.town,
                        estate.house.estateType,
                        estate.address.buildingName,
                        estate.house.currentFloors,
                        estate.house.rentableArea,
                        estate.state,
                        estate.house.sellingFee
                ))
                .from(estate)
                .where(ltEstateId(estateId))
                .where(estate.isPosted.eq(true))
                .orderBy(estate.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }


    @Override
    public List<AssetResponseDto> findAssetList(Long estateId) {
        QueryResults<AssetResponseDto> queryResults = jpaQueryFactory
                .select(new QAssetResponseDto(asset))
                .from(asset)
                .join(asset.estate, estate).fetchJoin()
                .where(asset.estate.id.eq(estateId))
                .fetchResults();
        return queryResults.getResults();
    }

    @Override
    public List<EstateModeDto> findOwnerEstateList(Long userId) {
        QueryResults<EstateModeDto> queryResults = jpaQueryFactory
                .select(new QEstateModeDto(estate.address, estate.house.estateType))
                .from(estate)
                .where(estate.owner.id.eq(userId))
                .fetchResults();
        return queryResults.getResults();
    }

    @Override
    public EstateModeDto findTenentEstateList(Long userId) {
        return jpaQueryFactory
                .select(new QEstateModeDto(estate.address, estate.house.estateType))
                .from(estate)
                .where(estate.tenent.id.eq(userId))
                .fetchOne();
    }

    @Override
    public EstateHit findEstateHit(Long estateId) {
        return jpaQueryFactory.select(estate.estateHit)
                .from(estate)
                .where(estate.id.eq(estateId))
                .fetchOne();
    }

    @Override
    public House findHouse(Long estateId) {
        return jpaQueryFactory.select(new QHouse(estate.house))
                .from(estate)
                .where(estate.id.eq(estateId))
                .fetchOne();
    }
}


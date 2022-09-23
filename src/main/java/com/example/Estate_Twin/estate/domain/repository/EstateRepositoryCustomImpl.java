package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.address.data.entity.QAddress;
import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.address.web.dto.QAddressDto;
import com.example.Estate_Twin.asset.data.entity.QAsset;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.QAssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.QHouse;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.example.Estate_Twin.house.web.dto.QHouseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstateRepositoryCustomImpl extends QuerydslRepositorySupport implements EstateRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;

    private QEstate estate;
    private QHouse house;
    private QAddress address;
    private QEstateHit estateHit;
    private QAsset asset;
    public EstateRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Estate.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.estate = QEstate.estate;
        this.house = QHouse.house;
        this.address = QAddress.address;
        this.estateHit = QEstateHit.estateHit;
        this.asset = QAsset.asset;
    }
    @Override
    public List<EstateMainDto> findByBoroughOrderByWeeklyHit(String borough) {
        QueryResults<EstateMainDto> queryResults = jpaQueryFactory
                .select(new QEstateMainDto(
                        estate.id,
                        estate.estateThumbNail,
                        estate.town,
                        estate.thumbnail3D,
                        estate.transactionType,
                        estate.house.sellingFee,
                        estate.house.estateType
                ))
                .from(estate)
                .leftJoin(estate.house, house)
                .leftJoin(estate.estateHit, estateHit)
                .where(estate.borough.eq(borough))
                .orderBy(estate.estateHit.weeklyHit.desc())
                .fetchResults();
        List<EstateMainDto> result = queryResults.getResults();
        return result;
    }

    @Override
    public List<EstateListResponseDto> findEstateList() {
        QueryResults<EstateListResponseDto> queryResults = jpaQueryFactory
                .select(new QEstateListResponseDto(
                        estate.id,
                        estate.transactionType,
                        estate.estateThumbNail,
                        estate.town,
                        house.estateType,
                        address.buildingName,
                        estate.house.currentFloors,
                        estate.house.rentableArea,
                        estate.state.stringValue()
                ))
                .from(estate)
                .leftJoin(estate.house, house)
                .leftJoin(estate.address, address)
                .orderBy(estate.id.desc())
                .fetchResults();
        List<EstateListResponseDto> result = queryResults.getResults();
        return result;
    }

    @Override
    public List<AssetResponseDto> findAssetList(Long estateId) {
        QueryResults<AssetResponseDto> queryResults = jpaQueryFactory
                .select(new QAssetResponseDto(asset))
                .from(asset)
                .leftJoin(asset.estate, estate)
                .where(asset.estate.id.eq(estateId))
                .fetchResults();
        return queryResults.getResults();
    }

    @Override
    public AddressDto findAddress(Long estateId) {
        return jpaQueryFactory.select(new QAddressDto(address))
                .from(estate)
                .leftJoin(estate.address, address)
                .where(estate.id.eq(estateId))
                .fetchOne();
    }

    @Override
    public EstateHitDto findEstateHit(Long estateId) {
        return jpaQueryFactory.select(new QEstateHitDto(estateHit))
                .from(estate)
                .leftJoin(estate.estateHit, estateHit)
                .where(estate.id.eq(estateId))
                .fetchOne();
    }

    @Override
    public HouseDto findHouse(Long estateId) {
        return jpaQueryFactory.select(new QHouseDto(house))
                .from(estate)
                .leftJoin(estate.house, house)
                .where(estate.id.eq(estateId))
                .fetchOne();
    }
}

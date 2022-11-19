package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.estate.web.dto.QEstateListResponseDto;
import com.example.Estate_Twin.house.domain.entity.QHouse;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PreferEstateRepositoryCustomImpl  extends QuerydslRepositorySupport implements PreferEstateRepositoryCustom{
    private JPAQueryFactory jpaQueryFactory;

    private QPreferEstate preferEstate;
    private QEstate estate;
    private QHouse house;

    public PreferEstateRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(PreferEstate.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.estate = QEstate.estate;
        this.house = QHouse.house;
        this.preferEstate = QPreferEstate.preferEstate;
    }

    @Override
    public boolean existsByEstateIdAndUserIdAndPrefer(Long estateId, Long userId, Preference prefer) {
        return from(preferEstate)
                .where(preferEstate.estate.id.eq(estateId), preferEstate.user.id.eq(userId), preferEstate.preference.eq(prefer))
                .select(preferEstate.id)
                .fetchFirst() != null;
    }

    @Override
    public Page<EstateListResponseDto> findByUserIdAndPrefer(Long userId, Preference prefer, Pageable pageable) {
        QueryResults<EstateListResponseDto> queryResults = jpaQueryFactory
                .select(new QEstateListResponseDto(
                        estate,
                        estate.house
                ))
                .from(preferEstate)
                .leftJoin(preferEstate.estate, estate)
                .leftJoin(estate.house, house)
                .where(preferEstate.preference.eq(prefer))
                .where(preferEstate.user.id.eq(userId))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}

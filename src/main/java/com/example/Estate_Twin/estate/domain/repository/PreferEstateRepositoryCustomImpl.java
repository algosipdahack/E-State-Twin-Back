package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PreferEstateRepositoryCustomImpl  extends QuerydslRepositorySupport implements PreferEstateRepositoryCustom{
    private JPAQueryFactory jpaQueryFactory;

    private QPreferEstate preferEstate;

    public PreferEstateRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(PreferEstate.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.preferEstate = QPreferEstate.preferEstate;
    }

    @Override
    public boolean existsByEstateIdAndUserIdAndPrefer(Long estateId, Long userId, Preference prefer) {
        return from(preferEstate)
                .where(preferEstate.estate.id.eq(estateId), preferEstate.user.id.eq(userId), preferEstate.preference.eq(prefer))
                .select(preferEstate.id)
                .fetchFirst() != null;
    }
}

package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstateRepositoryCustomImpl extends QuerydslRepositorySupport implements EstateRepositoryCustom {

    public EstateRepositoryCustomImpl() {
        super(Estate.class);
    }

    @Override
    public List<Estate> findByBoroughOrderByWeeklyHit(String borough) {
        QEstate estate = QEstate.estate;

        List<Estate> estateList = from(estate)
                .where(estate.borough.eq(borough))
                .select(estate)
                .orderBy(estate.estateHit.weeklyHit.desc())
                .fetch();
        return estateList;
    }
}

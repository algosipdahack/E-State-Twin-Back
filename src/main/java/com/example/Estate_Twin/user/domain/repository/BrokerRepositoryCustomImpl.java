package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.QEstate;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.estate.web.dto.EstateMainDto;
import com.example.Estate_Twin.estate.web.dto.QBrokerEstateDto;
import com.example.Estate_Twin.estate.web.dto.QEstateMainDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.QBroker;
import com.example.Estate_Twin.user.domain.entity.QUser;
import com.example.Estate_Twin.user.web.dto.BrokerListDto;
import com.example.Estate_Twin.user.web.dto.QBrokerListDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrokerRepositoryCustomImpl extends QuerydslRepositorySupport implements BrokerRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;
    private QBroker broker;
    private QUser user;
    private QEstate estate;
    public BrokerRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(Broker.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.broker = QBroker.broker;
    }
    @Override
    public List<BrokerListDto> getBrokerList() {
        QueryResults<BrokerListDto> queryResults = jpaQueryFactory
                .select(new QBrokerListDto(
                    broker.id,
                        broker.businessName,
                        broker.countOfTransactionCompletion,
                        broker.content,
                        broker.address,
                        broker.user.phone,
                        broker.brokerPhoto
                ))
                .from(broker)
                .fetchResults();
        List<BrokerListDto> result = queryResults.getResults();
        return result;
    }
}

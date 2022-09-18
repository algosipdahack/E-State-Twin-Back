package com.example.Estate_Twin.contractstate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.QContractState;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.QContractStateResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractStateRepositoryImpl extends QuerydslRepositorySupport implements ContractStateRepositoryCustom{
    private JPAQueryFactory jpaQueryFactory;
    private QContractState contractState;
    public ContractStateRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ContractState.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.contractState = QContractState.contractState;
    }

    @Override
    public List<ContractStateResponseDto> findByEstateIdOrderByDate(Long estateId) {
        return jpaQueryFactory.select(new QContractStateResponseDto(contractState))
                .from(contractState)
                .where(contractState.estate.id.eq(estateId))
                .orderBy(contractState.createdDate.asc())
                .fetch();
    }
}

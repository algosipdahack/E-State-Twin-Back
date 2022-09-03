package com.example.Estate_Twin.contractstate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.QContractState;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractStateRepositoryImpl extends QuerydslRepositorySupport implements ContractStateRepositoryCustom{
    public ContractStateRepositoryImpl() { super(ContractState.class); }

    @Override
    public List<ContractState> findByEstateIdOrderByDate(Long estateId) {
        QContractState contractState = QContractState.contractState;
        List<ContractState> contractStateList = from(contractState)
                .where(contractState.estate.id.eq(estateId))
                .select(contractState)
                .orderBy(contractState.createdDate.desc())
                .fetch();
        return contractStateList;
    }
}

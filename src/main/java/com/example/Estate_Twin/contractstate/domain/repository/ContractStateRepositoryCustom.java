package com.example.Estate_Twin.contractstate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;

import java.util.List;

public interface ContractStateRepositoryCustom {
    List<ContractState> findByEstateIdOrderByDate(Long estateId);
}

package com.example.Estate_Twin.contractstate.domain.dao;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;

import java.util.List;


public interface ContractStateDAO {
    ContractState updateState(ContractState contractState, Estate estate);
    List<ContractState> findContractState(Long estateId);
}

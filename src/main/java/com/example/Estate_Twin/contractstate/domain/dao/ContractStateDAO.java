package com.example.Estate_Twin.contractstate.domain.dao;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;


public interface ContractStateDAO {
    ContractState saveContractState(ContractState contractState, Estate estate);
    ContractState findContractState(Long id);
    ContractState updateState(Long id, State state);
}

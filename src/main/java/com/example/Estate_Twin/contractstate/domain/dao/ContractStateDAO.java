package com.example.Estate_Twin.contractstate.domain.dao;

import com.example.Estate_Twin.contractstate.domain.entity.*;


public interface ContractStateDAO {
    ContractState saveContractState(ContractState contractState);
    ContractState findContractState(Long id);
    ContractState updateState(Long id, State state);
}

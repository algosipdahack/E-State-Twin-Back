package com.example.Estate_Twin.contractstate.domain.dao;

import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.State;

import java.time.LocalDateTime;

public interface ContractStateDAO {
    ContractState saveContractState(ContractState contractState);
    ContractState findContractState(Long id);
    ContractState updateState(Long id, State state);
}

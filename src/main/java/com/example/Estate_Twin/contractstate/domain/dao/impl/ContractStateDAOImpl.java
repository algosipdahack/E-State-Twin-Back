package com.example.Estate_Twin.contractstate.domain.dao.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContractStateDAOImpl implements ContractStateDAO {
    private ContractStateRepository contractStateRepository;

    @Override
    public ContractState saveContractState(ContractState contractState, Estate estate) {
        contractState.setEstate(estate);
        return contractStateRepository.save(contractState);
    }

    @Override
    public ContractState findContractState(Long id) {
        return contractStateRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 거래 상태가 존재하지 않습니다. id = "+id));
    }

    @Override
    public ContractState updateState(Long id, State state) {
        ContractState contractState = contractStateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 거래 상태가 존재하지 않습니다. id = "+ id))
                .builder()
                .state(state)
                .build();
        return contractStateRepository.save(contractState);
    }
}

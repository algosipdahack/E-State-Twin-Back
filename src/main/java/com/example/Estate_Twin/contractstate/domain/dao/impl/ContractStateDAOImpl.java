package com.example.Estate_Twin.contractstate.domain.dao.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ContractStateDAOImpl implements ContractStateDAO {
    private ContractStateRepository contractStateRepository;
    private EstateRepository estateRepository;

    @Override
    public ContractState updateState(ContractState contractState, Estate estate) {
        contractState.setEstate(estate);

        estate.setState(contractState.getState());
        estateRepository.save(estate);
        
        return contractStateRepository.save(contractState);
    }

    @Override
    public List<ContractState> findContractState(Long estateId) {
        return contractStateRepository.findByEstateIdOrderByDate(estateId);
    }
}

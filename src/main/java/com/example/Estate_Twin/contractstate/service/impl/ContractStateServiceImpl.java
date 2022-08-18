package com.example.Estate_Twin.contractstate.service.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateSaveRequestDto;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class ContractStateServiceImpl implements ContractStateService {
    private final ContractStateDAO contractStateDAO;
    private final EstateDAO estateDAO;
    @Override
    public ContractStateResponseDto getContractState(Long id) {
        return new ContractStateResponseDto(contractStateDAO.findContractState(id));
    }

    @Override
    public ContractStateResponseDto saveContractState(Long estateId, ContractStateSaveRequestDto contractStateSaveRequestDto) {
        Estate estate = estateDAO.findEstate(estateId);
        contractStateSaveRequestDto.setEstate(estate);
        return new ContractStateResponseDto(contractStateSaveRequestDto.toEntity());
    }

    @Override
    public ContractStateResponseDto updateState(Long id, State state) {
        ContractState contractState = contractStateDAO.updateState(id,state);
        return new ContractStateResponseDto(contractState);
    }
}

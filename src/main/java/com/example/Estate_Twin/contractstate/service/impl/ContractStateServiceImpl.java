package com.example.Estate_Twin.contractstate.service.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.*;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;
import org.springframework.stereotype.Service;

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
        return new ContractStateResponseDto(contractStateDAO.saveContractState(contractStateSaveRequestDto.toEntity(),estateDAO.findEstate(estateId)));

    }

    @Override
    public ContractStateResponseDto updateState(Long id, State state) {
        return new ContractStateResponseDto(contractStateDAO.updateState(id,state));
    }
}

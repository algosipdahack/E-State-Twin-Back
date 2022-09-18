package com.example.Estate_Twin.contractstate.service.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.*;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractStateServiceImpl implements ContractStateService {
    private final ContractStateDAO contractStateDAO;
    private final EstateDAO estateDAO;

    @Override
    public List<ContractStateResponseDto> getContractState(Long estateId) {
        return contractStateDAO.findContractState(estateId);
    }


    @Override
    public ContractStateResponseDto updateState(Long estateId, ContractStateUpdateRequestDto contractStateUpdateRequestDto) {
        return new ContractStateResponseDto(contractStateDAO.updateState(contractStateUpdateRequestDto.toEntity(),estateDAO.findEstate(estateId)));
    }
}

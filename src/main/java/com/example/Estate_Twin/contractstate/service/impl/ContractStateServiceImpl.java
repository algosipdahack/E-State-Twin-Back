package com.example.Estate_Twin.contractstate.service.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class ContractStateServiceImpl implements ContractStateService {
    private final ContractStateDAO contractStateDAO;
    public ContractStateResponseDto getContractState(Long id) {
        return new ContractStateResponseDto(contractStateDAO.findContractState(id));
    }

    @Transactional
    public Long saveContractState(Long estateId, ContractStateSaveRequestDto contractStateSaveRequestDto) {
        Estate estate = estateRepository.findById(estateId)
                .orElseThrow(()->new IllegalArgumentException("해당 매물이 없습니다. id = "+estateId));
        contractStateSaveRequestDto.setEstate(estate);
        return contractStateRepository.save(contractStateSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long updateState(Long id, String state) {
        ContractState contractState = contractStateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
        contractState.updateState(state);
        return id;
    }
}

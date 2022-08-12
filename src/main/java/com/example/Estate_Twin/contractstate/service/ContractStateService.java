package com.example.Estate_Twin.contractstate.service;

import com.example.Estate_Twin.contractstate.domain.ContractState;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateSaveRequestDto;
import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContractStateService {
        private final ContractStateRepository contractStateRepository;
        private final EstateRepository estateRepository;
        public ContractStateResponseDto findById(Long id) {
            ContractState contractState = contractStateRepository.findById(id)
                    .orElseThrow(()->new IllegalArgumentException("해당 거래 상태가 없습니다. id = "+id));
            return new ContractStateResponseDto(contractState);
        }

        @Transactional
        public Long save(Long estateId, ContractStateSaveRequestDto contractStateSaveRequestDto) {
            Estate estate = estateRepository.findById(estateId)
                    .orElseThrow(()->new IllegalArgumentException("해당 매물이 없습니다. id = "+estateId));
            contractStateSaveRequestDto.setEstate(estate);
            return contractStateRepository.save(contractStateSaveRequestDto.toEntity()).getId();
        }

        @Transactional
        public Long update(Long id, String state) {
            ContractState contractState = contractStateRepository.findById(id)
                    .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
            contractState.updateState(state);
            return id;
        }
}

package com.example.Estate_Twin.contractstate.service;

import com.example.Estate_Twin.contractstate.web.dto.*;

public interface ContractStateService {
        ContractStateResponseDto getContractState(Long id);
        public Long saveContractState(Long estateId, ContractStateSaveRequestDto contractStateSaveRequestDto);
        public Long updateState(Long id, String state);
}

package com.example.Estate_Twin.contractstate.service;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.web.dto.*;

public interface ContractStateService {
        ContractStateResponseDto getContractState(Long id);
        ContractStateResponseDto saveContractState(Long estateId, ContractStateSaveRequestDto contractStateSaveRequestDto);
        ContractStateResponseDto updateState(Long id, State state);
}

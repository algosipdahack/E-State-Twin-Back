package com.example.Estate_Twin.contractstate.service;

import com.example.Estate_Twin.contractstate.web.dto.*;

import java.util.List;

public interface ContractStateService {
        List<ContractStateResponseDto> getContractState(Long estateId);
        ContractStateResponseDto updateState(Long estateId, ContractStateUpdateRequestDto contractStateUpdateRequestDto);
}

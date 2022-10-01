package com.example.Estate_Twin.contractstate.service;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.web.dto.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;

import java.util.List;

public interface ContractStateService {
        List<ContractStateResponseDto> getContractState(Long estateId);
        ContractStateResponseDto updateState(Long estateId, State state);
}

package com.example.Estate_Twin.contractstate.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;

import java.util.List;

public interface ContractStateRepositoryCustom {
    List<ContractStateResponseDto> findByEstateIdOrderByDate(Long estateId);
    ContractState findByEstateIdAndState(Long estateId, State state);
}

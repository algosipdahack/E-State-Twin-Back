package com.example.Estate_Twin.contractstate.service.impl;

import com.example.Estate_Twin.contractstate.domain.dao.impl.ContractStateDAOImpl;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.*;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractStateServiceImpl implements ContractStateService {
    private final ContractStateDAOImpl contractStateDAO;
    private final EstateDAOImpl estateDAO;
    private ContractStateRepository contractStateRepository;

    @Override
    public List<ContractStateResponseDto> getContractState(Long estateId) {
        return contractStateRepository.findByEstateIdOrderByDate(estateId);
    }

    @Override
    public ContractStateResponseDto updateState(Long estateId, State state) {
        return new ContractStateResponseDto(contractStateDAO.updateState(estateDAO.getEstate(estateId), state));
    }
}

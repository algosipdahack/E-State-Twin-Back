package com.example.Estate_Twin.contractstate.domain.dao.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.exception.ContractStateDuplicateException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class ContractStateDAOImpl implements ContractStateDAO {
    private ContractStateRepository contractStateRepository;

    //상태 체크 -> 중개인, 소유자 모두 confirm을 눌러야만 변경 가능
    @Override
    @Transactional
    public ContractState updateState(Estate estate, State state) {
        if (estate.getState() == state) {
            throw new ContractStateDuplicateException("해당 ContractState는 이미 존재합니다.", ErrorCode.CONTRACT_STATE_DUPLICATION);
        }
        estate.setState(state);
        return contractStateRepository.save(new ContractState(state, estate));
    }

    @Override
    public List<ContractStateResponseDto> findContractState(Long estateId) {
        return contractStateRepository.findByEstateIdOrderByDate(estateId);
    }

}

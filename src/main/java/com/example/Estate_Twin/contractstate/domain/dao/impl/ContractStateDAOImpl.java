package com.example.Estate_Twin.contractstate.domain.dao.impl;

import com.example.Estate_Twin.contractstate.domain.dao.ContractStateDAO;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.exception.BadRequestException;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ContractStateDAOImpl implements ContractStateDAO {
    private ContractStateRepository contractStateRepository;
    private EstateRepository estateRepository;

    //상태 체크 -> 중개인, 소유자 모두 confirm을 눌러야만 변경 가능
    @Override
    public ContractState updateState(ContractState contractState, Estate estate) {
        if(contractState.getState() != State.CONTRACT_BEFORE) {
            if (estate.isBrokerConfirmYN() == true && estate.isOwnerConfirmYN() == true) {
                contractState.setEstate(estate);

                estate.setState(contractState.getState());
                estateRepository.save(estate);

                return contractStateRepository.save(contractState);
            }
            throw new BadRequestException("중개인과 소유자 모두 Confirm을 눌러야만 합니다!");
        }
        return contractStateRepository.save(contractState);
    }

    @Override
    public List<ContractStateResponseDto> findContractState(Long estateId) {
        return contractStateRepository.findByEstateIdOrderByDate(estateId);
    }
}

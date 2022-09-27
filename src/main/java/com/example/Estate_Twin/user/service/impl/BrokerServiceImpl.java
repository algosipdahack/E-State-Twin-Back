package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.dao.*;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    private final BrokerDAO brokerDAO;
    private final UserDAO userDAO;
    @Override
    public BrokerResponseDto getBroker(String userEmail) {
        return new BrokerResponseDto(brokerDAO.findBrokerByEmail(userEmail));
    }

    @Override
    public BrokerResponseDto signUpBroker(String userEmail, BrokerSignUpDto brokerSignUpDto) {
        User user = userDAO.findUserByEmail(userEmail);
        return new BrokerResponseDto(brokerDAO.signUp(brokerSignUpDto.toEntity(), user));
    }

    @Override
    public List<BrokerListDto> getBrokerList() {
        return brokerDAO.getBrokerList();
    }

    @Override
    public List<BrokerEstateDto> getbrokerEstate(Long brokerId, State state) {
        return brokerDAO.getBrokerEstate(brokerId, state);
    }

}

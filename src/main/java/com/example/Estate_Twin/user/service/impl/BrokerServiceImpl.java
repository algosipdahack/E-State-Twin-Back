package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.user.domain.dao.*;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return new BrokerResponseDto(brokerDAO.signUp(brokerSignUpDto.toEntity(),userDAO.findUserByEmail(userEmail)));
    }
}

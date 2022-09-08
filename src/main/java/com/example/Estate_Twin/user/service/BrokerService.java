package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.BrokerResponseDto;
import com.example.Estate_Twin.user.web.dto.BrokerSignUpDto;

public interface BrokerService {
    BrokerResponseDto getBroker(Long userId);
    BrokerResponseDto signUpBroker(Long userId, BrokerSignUpDto brokerSignUpDto);
}

package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.*;

public interface BrokerService {
    BrokerResponseDto getBroker(String userEmail);
    BrokerResponseDto signUpBroker(String userEmail, BrokerSignUpDto brokerSignUpDto);
}

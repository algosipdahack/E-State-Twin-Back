package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.*;

import java.util.List;

public interface BrokerService {
    BrokerResponseDto getBroker(String userEmail);
    BrokerResponseDto signUpBroker(String userEmail, BrokerSignUpDto brokerSignUpDto);
    List<BrokerListDto> getBrokerList();
    //List<BrokerEstateDto> getbrokerEstate(Long brokerId, State state);
}

package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.web.dto.*;

import java.util.List;

public interface BrokerService {
    BrokerResponseDto getBroker(String userEmail);
    BrokerResponseDto signUpBroker(String userEmail, BrokerSignUpDto brokerSignUpDto);
    List<BrokerListDto> getBrokerList();
    List<BrokerEstateDto> getbrokerEstate(String email, State state);
}

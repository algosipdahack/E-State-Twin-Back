package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.web.dto.*;

import java.util.List;

public interface BrokerService {
    BrokerSummaryDto getBroker(String userEmail);
    BrokerSummaryDto signUpBroker(User user, BrokerSignUpDto brokerSignUpDto);
    List<BrokerListDto> getBrokerList();
    List<BrokerEstateDto> getbrokerEstate(String email, State state);
}

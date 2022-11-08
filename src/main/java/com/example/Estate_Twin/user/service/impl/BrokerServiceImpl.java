package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.dao.impl.*;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    private final BrokerDAOImpl brokerDAO;
    @Override
    public BrokerSummaryDto getBroker(String userEmail) {
        Broker broker = brokerDAO.findBrokerByEmail(userEmail);
        return new BrokerSummaryDto(broker);
    }

    @Override
    public BrokerSummaryDto signUpBroker(User user, BrokerSignUpDto brokerSignUpDto) {
        Broker broker = brokerDAO.signUp(brokerSignUpDto.toEntity(), user);
        return new BrokerSummaryDto(broker);
    }

    @Override
    public List<BrokerListDto> getBrokerList() {
        return brokerDAO.getBrokerList();
    }

    @Override
    public List<BrokerEstateDto> getbrokerEstate(String email, State state) {
        Long brokerId = brokerDAO.findBrokerByEmail(email).getId();
        return brokerDAO.getBrokerEstate(brokerId, state);
    }

}

package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.BrokerRepository;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    BrokerRepository brokerRepository;
    EstateRepository estateRepository;

    @Override
    public BrokerSummaryDto getBrokerWithUser(User user) {
        Broker broker = brokerRepository.findByUserEmailWithUserUsingFetchJoin(user.getEmail()).orElseThrow(()-> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
        return new BrokerSummaryDto(broker);
    }

    @Override
    public BrokerSummaryDto signUpBroker(User user, BrokerSignUpDto brokerSignUpDto) {
        Broker broker = brokerSignUpDto.toEntity();
        broker.setUser(user);
        brokerRepository.save(broker);
        return new BrokerSummaryDto(broker);
    }

    @Override
    public List<BrokerListDto> getBrokerList() {
        return brokerRepository.getBrokerList();
    }

    @Override
    public List<BrokerEstateDto> getBrokerEstate(String email, State state) {
        return estateRepository.findEstateByEmailAndState(email,state);
    }

}

package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.dao.*;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    private final BrokerDAO brokerDAO;
    private final UserDAO userDAO;
    private final AddressDAO addressDAO;
    @Override
    public BrokerResponseDto getBroker(String userEmail) {
        return new BrokerResponseDto(brokerDAO.findBrokerByEmail(userEmail));
    }

    @Override
    public BrokerResponseDto signUpBroker(String userEmail, BrokerSignUpDto brokerSignUpDto) {
        User user = userDAO.findUserByEmail(userEmail);
        Address address = addressDAO.saveAddress(brokerSignUpDto.getAddress().toEntity());
        return new BrokerResponseDto(brokerDAO.signUp(brokerSignUpDto.toEntity(), user, address));
    }

    @Override
    public List<BrokerListDto> getBrokerList() {
        return brokerDAO.getBrokerList();
    }
/*
    @Override
    public List<BrokerEstateDto> getbrokerEstate(Long brokerId, State state) {
        return brokerDAO.getBrokerEstate(brokerId, state);
    }*/

}

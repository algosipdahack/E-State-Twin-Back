package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.web.dto.BrokerListDto;

import java.util.List;

public interface BrokerDAO {
    Broker findBrokerByEmail(String Email);
    Broker signUp(Broker broker, User user);
    List<BrokerListDto> getBrokerList();
    Broker findBrokerById(Long id);
    List<BrokerEstateDto> getBrokerEstate(Long brokerId, State state);
}

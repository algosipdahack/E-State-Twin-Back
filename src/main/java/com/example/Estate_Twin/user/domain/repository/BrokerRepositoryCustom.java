package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.web.dto.BrokerListDto;

import java.util.List;

public interface BrokerRepositoryCustom {
    List<BrokerListDto> getBrokerList();

}

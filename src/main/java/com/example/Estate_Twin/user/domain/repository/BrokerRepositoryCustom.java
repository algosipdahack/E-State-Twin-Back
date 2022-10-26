package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.user.web.dto.BrokerListDto;

import java.util.List;

public interface BrokerRepositoryCustom {
    List<BrokerListDto> getBrokerList();
}

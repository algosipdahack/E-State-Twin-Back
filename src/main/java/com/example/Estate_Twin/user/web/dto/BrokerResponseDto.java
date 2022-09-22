package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.estate.web.dto.EstateDto;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import lombok.Getter;

import java.util.*;

@Getter
public class BrokerResponseDto {
    private final Long id;
    private final String businessName;
    private final String agentName;
    private final String brokerageRegistrationNumber;
    private final String businessRegistrationNumber;
    private final String businessLicense;
    private final String brokerageRegistrationLicense;
    private final UserDto user;
    private final List<EstateDto> estates;
    private List<MediaDto> brokerPhotos;

    public BrokerResponseDto(Broker broker) {
        this.id = broker.getId();
        this.businessName = broker.getBusinessName();
        this.agentName = broker.getAgentName();
        this.brokerageRegistrationNumber = broker.getBrokerageRegistrationNumber();
        this.businessRegistrationNumber = broker.getBusinessRegistrationNumber();
        this.businessLicense = broker.getBusinessLicense();
        this.brokerageRegistrationLicense = broker.getBrokerageRegistrationLicense();
        this.user = new UserDto(broker.getUser());
        this.estates = new ArrayList<>();
        broker.getTradeEstates().forEach(estate -> this.estates.add(new EstateDto(estate)));
        this.brokerPhotos = new ArrayList<>();
        broker.getBrokerPhoto().forEach(brokerPhoto -> this.brokerPhotos.add(new MediaDto(brokerPhoto)));
    }
}

package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.estate.web.dto.EstateDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.*;

@Getter
public class BrokerResponseDto {
    private final Long id;
    @Schema(description = "상호명")
    private final String businessName;
    @Schema(description = "대표명")
    private final String agentName;
    @Schema(description = "중개 등록번호")
    private final String brokerageRegistrationNumber;
    @Schema(description = "사업자 등록번호")
    private final String businessRegistrationNumber;
    @Schema(description = "사업자 등록증")
    private final String businessLicense;
    @Schema(description = "중개 등록증")
    private final String brokerageRegistrationLicense;
    private final Long countOfTransactionCompletion;
    private final String content;
    private final AddressDto address;
    private final String phone;
    private final List<EstateDto> estates;
    private final String brokerPhoto;

    @QueryProjection
    public BrokerResponseDto(Broker broker) {
        this.id = broker.getId();
        this.businessName = broker.getBusinessName();
        this.agentName = broker.getAgentName();
        this.brokerageRegistrationNumber = broker.getBrokerageRegistrationNumber();
        this.businessRegistrationNumber = broker.getBusinessRegistrationNumber();
        this.businessLicense = broker.getBusinessLicense();
        this.brokerageRegistrationLicense = broker.getBrokerageRegistrationLicense();
        this.countOfTransactionCompletion = broker.getCountOfTransactionCompletion();
        this.content = broker.getContent();
        this.address = new AddressDto(broker.getAddress());
        this.phone = broker.getUser().getPhone();
        this.brokerPhoto = broker.getBrokerPhoto();
        this.estates = new ArrayList<>();
        broker.getTradeEstates().forEach(estate -> this.estates.add(new EstateDto(estate)));
    }
}

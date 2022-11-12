package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.AddressResponseDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BrokerDetailDto {
    private final Long id;
    @Schema(description = "상호명")
    private final String businessName;
    @Schema(description = "대표명")
    private final String agentName;
    private final AddressResponseDto address;
    private final String phone;
    private final String brokerPhoto;

    public BrokerDetailDto(Broker broker) {
        this.id = broker.getId();
        this.businessName = broker.getBusinessName();
        this.agentName = broker.getAgentName();
        this.address = new AddressResponseDto(broker.getAddress());
        this.phone = broker.getUser().getPhone();
        this.brokerPhoto = broker.getBrokerPhoto();
    }
}

package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.user.domain.entity.Broker;
import lombok.*;

@Getter
@NoArgsConstructor
public class BrokerSignUpDto {
    private String businessName;
    private String agentName;
    private String brokerageRegistrationNumber;
    private String businessRegistrationNumber;
    private String businessLicense;
    private String brokerageRegistrationLicense;

    public Broker toEntity() {
        return Broker.builder()
                .businessName(businessName)
                .businessRegistrationNumber(businessRegistrationNumber)
                .businessLicense(businessLicense)
                .agentName(agentName)
                .brokerageRegistrationNumber(brokerageRegistrationNumber)
                .brokerageRegistrationLicense(brokerageRegistrationLicense)
                .build();
    }
}

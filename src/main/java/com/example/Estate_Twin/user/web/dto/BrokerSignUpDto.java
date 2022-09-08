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

    @Builder
    public BrokerSignUpDto(String businessName, String agentName, String brokerageRegistrationNumber,
                           String businessRegistrationNumber, String businessLicense, String brokerageRegistrationLicense) {
        this.businessName = businessName;
        this.agentName = agentName;
        this.brokerageRegistrationNumber = brokerageRegistrationNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessLicense = businessLicense;
        this.brokerageRegistrationLicense = brokerageRegistrationLicense;
    }

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

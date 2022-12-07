package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.user.domain.entity.Broker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerSignUpDto {
    @NotNull
    @Schema(description = "상호명")
    private String businessName;
    @NotNull
    @Schema(description = "대표명")
    private String agentName;
    @NotNull
    @Schema(description = "중개 등록번호")
    private String brokerageRegistrationNumber;
    @NotNull
    @Schema(description = "사업자 등록번호")
    private String businessRegistrationNumber;
    @NotNull
    @Schema(description = "사업자 등록증")
    private String businessLicense;
    @NotNull
    @Schema(description = "중개등록증")
    private String brokerageRegistrationLicense;
    @NotNull
    @Schema(description = "공인중개사 사진")
    private String brokerPhoto;
    private String content;
    @NotNull
    private Address address;

    public Broker toEntity() {
        return Broker.builder()
                .businessName(businessName)
                .businessRegistrationNumber(businessRegistrationNumber)
                .agentName(agentName)
                .brokerageRegistrationNumber(brokerageRegistrationNumber)
                .businessLicense(businessLicense)
                .brokerageRegistrationLicense(brokerageRegistrationLicense)
                .brokerPhoto(brokerPhoto)
                .content(content)
                .address(address)
                .build();
    }
}

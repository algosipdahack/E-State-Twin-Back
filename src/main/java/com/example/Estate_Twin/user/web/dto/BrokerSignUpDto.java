package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
public class BrokerSignUpDto {
    @Schema(description = "상호명")
    private String businessName;
    @Schema(description = "대표명")
    private String agentName;
    @Schema(description = "중개 등록번호")
    private String brokerageRegistrationNumber;
    @Schema(description = "사업자 등록번호")
    private String businessRegistrationNumber;
    @Schema(description = "사업자 등록증")
    private String businessLicense;
    @Schema(description = "중개등록증")
    private String brokerageRegistrationLicense;
    @Schema(description = "공인중개사 사진")
    private String brokerPhoto;
    private String content;
    private AddressSaveRequestDto address;

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
                .address(address.toEntity())
                .build();
    }
}

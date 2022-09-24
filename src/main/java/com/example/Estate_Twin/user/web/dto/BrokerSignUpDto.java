package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
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
    private MediaSaveMultipartRequestDto businessLicense;
    @Schema(description = "중개등록증")
    private MediaSaveMultipartRequestDto brokerageRegistrationLicense;
    @Schema(description = "공인중개사 사진")
    private MediaSaveMultipartRequestDto brokerPhoto;
    private AddressSaveRequestDto address;

    public Broker toEntity() {
        return Broker.builder()
                .businessName(businessName)
                .businessRegistrationNumber(businessRegistrationNumber)
                .agentName(agentName)
                .brokerageRegistrationNumber(brokerageRegistrationNumber)
                .address(address.toEntity())
                .build();
    }
}

package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.Address;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BrokerListDto {
    private final Long id;
    @Schema(description = "상호명")
    private final String businessName;
    @Schema(description = "거래 완증 건수")
    private final Long countOfTransactionCompletion;
    private final String content;
    private final Address address;
    private final String phone;
    private final String brokerPhoto;

    @QueryProjection
    public BrokerListDto(Long id, String businessName, Long countOfTransactionCompletion, String content, Address address, String phone, String brokerPhoto) {
        this.id = id;
        this.businessName = businessName;
        this.countOfTransactionCompletion = countOfTransactionCompletion;
        this.content = content;
        this.address = address;
        this.phone = phone;
        this.brokerPhoto = brokerPhoto;
    }

}

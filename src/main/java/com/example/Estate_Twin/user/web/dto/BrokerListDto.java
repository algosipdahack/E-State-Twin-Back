package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
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
    private final AddressDto address;
    private final String phone;
    private final MediaDto brokerPhoto;
    @QueryProjection
    public BrokerListDto(Long id, String businessName, Long countOfTransactionCompletion, String content, Address address, String phone, Media brokerPhoto) {
        this.id = id;
        this.businessName = businessName;
        this.countOfTransactionCompletion = countOfTransactionCompletion;
        this.content = content;
        this.address = new AddressDto(address);
        this.phone = phone;
        this.brokerPhoto = new MediaDto(brokerPhoto);
    }

}

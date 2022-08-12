package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.TransactionType;
import lombok.*;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String city;
    private String ad_distinct;
    private String address;

    @Builder
    public EstateSaveRequestDto(String transactionType,
                                String estateThumbNail, String content,
                                String ad_distinct, String address, String city) {
        this.transactionType = TransactionType.of(transactionType);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.ad_distinct = ad_distinct;
        this.address = address;
        this.city = city;
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .transactionType(transactionType)
                .ad_distinct(ad_distinct)
                .address(address)
                .city(city)
                .build();
    }

}

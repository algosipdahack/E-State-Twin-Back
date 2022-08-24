package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import lombok.*;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String city;
    private String borough;
    private AddressSaveRequestDto address;

    @Builder
    public EstateSaveRequestDto(String transactionType,
                                String estateThumbNail, String content,
                                String borough, AddressSaveRequestDto address, String city) {
        this.transactionType = TransactionType.of(transactionType);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.borough = borough;
        this.address = address;
        this.city = city;
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .transactionType(transactionType)
                .borough(borough)
                .city(city)
                .build();
    }

}

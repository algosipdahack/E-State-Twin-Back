package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateUpdateRequestDto;
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
    private String town;
    private AddressSaveRequestDto address;
    private ContractStateUpdateRequestDto contractState;
    @Builder
    public EstateSaveRequestDto(String transactionType,
                                String estateThumbNail, String content,
                                AddressSaveRequestDto address,
                                ContractStateUpdateRequestDto contractState) {
        this.transactionType = TransactionType.of(transactionType);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.address = address;
        this.contractState = contractState;
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.town = address.getTown();
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .transactionType(transactionType)
                .town(town)
                .city(city)
                .borough(borough)
                .build();
    }

}

package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.contractstate.domain.State;
import com.example.Estate_Twin.estate.domain.Rank;
import com.example.Estate_Twin.estate.domain.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {

    private TransactionType transactionType;
    private State state;
    private String estateThumbNail;
    private String content;

    private Rank rank;

    private String model;

    private String arCam;

    private String city;

    private String ad_distinct;

    private String address;

    private LocalDateTime modifiedDate;
    @Builder
    public EstateUpdateRequestDto(String transactionType, String state,
                                  String estateThumbNail, String content, String rank, String model,
                                  String arCam, String city, String ad_distinct, String address) {
        this.transactionType = TransactionType.of(transactionType);
        this.state = State.of(state);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.rank = Rank.of(rank);
        this.model = model;
        this.arCam = arCam;
        this.city = city;
        this.ad_distinct = ad_distinct;
        this.address = address;
        this.modifiedDate = LocalDateTime.now();
    }
}

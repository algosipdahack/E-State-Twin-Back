package com.example.Estate_Twin.web.dto;

import com.example.Estate_Twin.domain.estate.Estate;
import com.example.Estate_Twin.domain.estate.Rank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private String content;
    private Rank rank;
    private String model;
    private String arCam;

    @Builder
    public EstateSaveRequestDto(String content, String model, String arCam, Rank rank) {
        this.content = content;
        this.model = model;
        this.rank = rank;
        this.arCam = arCam;
    }

    /*public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .rank(rank)
                .model(model)
                .arCam(arCam)
                .build();
    }*/
}

package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.checklist.domain.CheckList;
import com.example.Estate_Twin.checklist.domain.RepairType;
import com.example.Estate_Twin.media.domain.Media;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
public class CheckListSaveRequestDto {
    private Asset asset;

    private String flawPart;

    private String category;

    private String checkListContent;

    private LocalDateTime repairDate;

    private RepairType repairType;

    private String manufacturer;

    private Boolean brokerConfirmYN;

    private Boolean ownerConfirmYN;

    @Builder
    public CheckListSaveRequestDto(String flawPart, String category, String checkListContent,
                                   LocalDateTime repairDate, String repairType, String manufacturer,
                                   Boolean brokerConfirmYN, Boolean ownerConfirmYN) {
        this.flawPart = flawPart;
        this.category = category;
        this.checkListContent = checkListContent;
        this.repairDate = repairDate;
        this.repairType = RepairType.of(repairType);
        this.manufacturer = manufacturer;
        this.brokerConfirmYN = brokerConfirmYN;
        this.ownerConfirmYN = ownerConfirmYN;
    }

    public CheckList toEntity() {
        return CheckList.builder()
                .checkListContent(checkListContent)
                .ownerConfirmYN(ownerConfirmYN)
                .brokerConfirmYN(brokerConfirmYN)
                .asset(asset)
                .category(category)
                .flawPart(flawPart)
                .manufacturer(manufacturer)
                .repairDate(repairDate)
                .repairType(repairType)
                .build();
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}

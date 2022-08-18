package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CheckListSaveRequestDto {
    private Asset asset;
    private String flawPart;
    private Category category;
    private String checkListContent;
    private LocalDateTime repairDate;
    private RepairType repairType;
    private String manufacturer;
    private Boolean brokerConfirmYN;
    private Boolean ownerConfirmYN;

    @Builder
    public CheckListSaveRequestDto(String flawPart, Category category, String checkListContent,
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

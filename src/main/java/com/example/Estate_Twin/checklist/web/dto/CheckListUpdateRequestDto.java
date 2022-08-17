package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.checklist.domain.Category;
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
public class CheckListUpdateRequestDto {
    private String flawPart;

    private Category category;

    private String checkListContent;

    private LocalDateTime repairDate;

    private RepairType repairType;

    private String manufacturer;

    private Boolean brokerConfirmYN;

    private Boolean ownerConfirmYN;

    @Builder
    public CheckListUpdateRequestDto(String flawPart, Category category, String checkListContent,
                                     LocalDateTime repairDate, RepairType repairType,
                                     String manufacturer, Boolean brokerConfirmYN, Boolean ownerConfirmYN) {
        this.flawPart = flawPart;
        this.category = category;
        this.checkListContent = checkListContent;
        this.repairDate = repairDate;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
        this.brokerConfirmYN = brokerConfirmYN;
        this.ownerConfirmYN = ownerConfirmYN;
    }
}

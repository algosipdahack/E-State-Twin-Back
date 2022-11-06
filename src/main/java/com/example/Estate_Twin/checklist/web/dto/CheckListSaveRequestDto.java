package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CheckListSaveRequestDto {
    private String flawPart;
    private String checkListContent;
    private LocalDate repairDate;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    private Boolean brokerConfirmYN;
    private Boolean ownerConfirmYN;
    private Boolean tenantConfirmYN;
    private String checkListPhoto;

    public CheckList toEntity() {
        return CheckList.builder()
                .checkListContent(checkListContent)
                .ownerConfirmYN(ownerConfirmYN)
                .brokerConfirmYN(brokerConfirmYN)
                .tenantConfirmYN(tenantConfirmYN)
                .flawPart(flawPart)
                .repairDate(repairDate)
                .checkListPhoto(checkListPhoto)
                .repairType(RepairType.of(repairType))
                .build();
    }

}

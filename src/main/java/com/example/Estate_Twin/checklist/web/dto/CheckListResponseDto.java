package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.*;

@Getter
public class CheckListResponseDto {
    private final Long id;
    private final String flawPart;
    private final String checkListContent;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private final String repairType;
    private final String checkListPhoto;
    private final Boolean brokerConfirmYN;
    private final Boolean ownerConfirmYN;
    private final Boolean tenantConfirmYN;
    private final Boolean totalConfirmYN;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate repairDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    @QueryProjection
    public CheckListResponseDto(CheckList checkList) {
        this.id = checkList.getId();
        this.checkListPhoto = checkList.getCheckListPhoto();
        this.flawPart = checkList.getFlawPart();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType().toString();
        this.brokerConfirmYN = checkList.getBrokerConfirmYN();
        this.ownerConfirmYN = checkList.getOwnerConfirmYN();
        this.tenantConfirmYN = checkList.getTenantConfirmYN();
        this.totalConfirmYN = checkList.getTotalConfirmYN();
        this.createdAt = checkList.getCreatedDate();
    }

}

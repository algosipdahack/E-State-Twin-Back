package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CheckListDto {
    private final String flawPart;
    private final String checkListContent;
    private final String checkListPhoto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime repairDate;
    private final RepairType repairType;
    private final Boolean brokerConfirmYN;
    private final Boolean tenantConfirmYN;
    private final Boolean ownerConfirmYN;

    @QueryProjection
    public CheckListDto(CheckList checkList) {
        this.checkListPhoto = this.getCheckListPhoto();
        this.flawPart = checkList.getFlawPart();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType();
        this.tenantConfirmYN = checkList.getTenantConfirmYN();
        this.brokerConfirmYN = checkList.getBrokerConfirmYN();
        this.ownerConfirmYN = checkList.getOwnerConfirmYN();
    }
}

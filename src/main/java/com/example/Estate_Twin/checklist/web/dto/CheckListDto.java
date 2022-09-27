package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CheckListDto {
    private final String checkListPhoto;
    private final String flawPart;
    private final String checkListContent;
    private final LocalDateTime repairDate;
    private final RepairType repairType;

    @QueryProjection
    public CheckListDto(CheckList checkList) {
        this.checkListPhoto = this.getCheckListPhoto();
        this.flawPart = checkList.getFlawPart();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType();
    }
}

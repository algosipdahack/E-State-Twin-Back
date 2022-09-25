package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;
@Getter
public class CheckListDto {
    private final List<MediaResponseDto> checkListPhotos;
    private final String flawPart;
    private final String checkListContent;
    private final LocalDateTime repairDate;
    private final RepairType repairType;

    @QueryProjection
    public CheckListDto(CheckList checkList) {
        this.checkListPhotos = new ArrayList<>();
        checkList.getCheckListPhoto().forEach(photo -> this.checkListPhotos.add(new MediaResponseDto(photo)));
        this.flawPart = checkList.getFlawPart();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType();
    }
}

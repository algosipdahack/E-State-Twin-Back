package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.web.dto.AssetDto;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CheckListResponseDto {
    private final Long id;
    private final String flawPart;
    private final String checkListContent;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private final String repairType;
    private final Boolean brokerConfirmYN;
    private final Boolean ownerConfirmYN;
    private final Boolean tanentConfirmYN;
    private final LocalDateTime repairDate;
    private final LocalDateTime createdAt;
    private AssetDto asset;
    private final List<MediaResponseDto> checkListPhotos;

    @QueryProjection
    public CheckListResponseDto(CheckList checkList) {
        this.id = checkList.getId();
        this.asset = null;
        this.checkListPhotos = new ArrayList<>();
        checkList.getCheckListPhoto().forEach(photo -> this.checkListPhotos.add(new MediaResponseDto(photo)));
        this.flawPart = checkList.getFlawPart();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType().toString();
        this.brokerConfirmYN = checkList.getBrokerConfirmYN();
        this.ownerConfirmYN = checkList.getOwnerConfirmYN();
        this.tanentConfirmYN = checkList.getTanentConfirmYN();
        this.createdAt = checkList.getCreatedDate();
    }
    public void setAsset(AssetDto asset) {
        this.asset = asset;
    }

}

package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.web.dto.AssetDto;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class CheckListDto {
    private final AssetDto asset;
    private final List<Media> checkListPhotos;
    private final String flawPart;
    private final Category category;
    private final String checkListContent;
    private final LocalDateTime repairDate;
    private final RepairType repairType;
    private final Boolean brokerConfirmYN;
    private final Boolean ownerConfirmYN;

    public CheckListDto(CheckList checkList) {
        this.asset = new AssetDto(checkList.getAsset());
        this.checkListPhotos = checkList.getCheckListPhoto();
        this.flawPart = checkList.getFlawPart();
        this.category = checkList.getCategory();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType();
        this.brokerConfirmYN = checkList.getBrokerConfirmYN();
        this.ownerConfirmYN = checkList.getOwnerConfirmYN();
    }
}

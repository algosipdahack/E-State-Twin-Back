package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CheckListResponseDto {
    private final AssetResponseDto asset;
    private final List<Media> checkListPhoto;
    private final String flawPart;
    private final Category category;
    private final String checkListContent;
    private final LocalDateTime repairDate;
    private final RepairType repairType;
    private final String manufacturer;
    private final Boolean brokerConfirmYN;
    private final Boolean ownerConfirmYN;

    public CheckListResponseDto(CheckList checkList) {
        this.asset = new AssetResponseDto(checkList.getAsset());
        this.checkListPhoto = checkList.getCheckListPhoto();
        this.flawPart = checkList.getFlawPart();
        this.category = checkList.getCategory();
        this.checkListContent = checkList.getCheckListContent();
        this.repairDate = checkList.getRepairDate();
        this.repairType = checkList.getRepairType();
        this.manufacturer = checkList.getManufacturer();
        this.brokerConfirmYN = checkList.getBrokerConfirmYN();
        this.ownerConfirmYN = checkList.getOwnerConfirmYN();
    }
}

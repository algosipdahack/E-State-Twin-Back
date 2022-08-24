package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.asset.web.dto.AssetDto;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class CheckListDto {
    private final Long id;
    private final AssetDto asset;
    private final List<Media> checkListPhoto;
    private final String flawPart;
    private final Category category;
    private final String checkListContent;
    private final LocalDateTime repairDate;
    private final RepairType repairType;
    private final String manufacturer;
    private final Boolean brokerConfirmYN;
    private final Boolean ownerConfirmYN;

    public CheckListDto(CheckList checkList) {
        this.id = checkList.getId();
        this.asset = new AssetDto(checkList.getAsset());
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

package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.checklist.web.dto.CheckListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class AssetDto {
    private final Category category;
    private final String assetPhoto;
    private final Option option;
    private final String productName;
    private final String manufacturer;
    private final String anchorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime repairDate;
    private final List<CheckListDto> checkLists;

    @QueryProjection
    public AssetDto(Asset asset) {
        this.category = asset.getCategory();
        this.assetPhoto = asset.getAssetPhoto();
        this.option = asset.getOption();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
        this.anchorId = asset.getAnchorId();
        this.repairDate = asset.getRepairDate();
        this.checkLists = new ArrayList<>();
        asset.getCheckLists().forEach(checkList -> this.checkLists.add(new CheckListDto(checkList)));
    }
}

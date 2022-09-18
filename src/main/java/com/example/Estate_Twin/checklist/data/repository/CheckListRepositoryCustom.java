package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetDto;

public interface CheckListRepositoryCustom {
    AssetDto findAssetbyId(Long checkListId);
}

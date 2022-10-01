package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;

import java.util.List;

public interface CheckListRepositoryCustom {
    List<CheckListResponseDto> findCheckListbyAssetId(Long assetId);
}

package com.example.Estate_Twin.checklist.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckListDAO {
    CheckList saveCheckList(CheckList checkList, Asset asset);
    CheckList findCheckList(Long id);
    CheckList updateCheckList(Long id, CheckListUpdateRequestDto dto);
    List<CheckList> findAllCheckList(Long assetId);
}

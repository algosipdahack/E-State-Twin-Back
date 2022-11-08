package com.example.Estate_Twin.checklist.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.User;

import java.util.List;

public interface CheckListDAO {
    CheckList saveCheckList(CheckList checkList, Asset asset);
    CheckList findCheckList(Long id);
    CheckList updateCheckList(Long id, CheckListUpdateRequestDto dto);
    CheckList confirmBroker(CheckList checkList);
    CheckList confirmUser(Long estateId, CheckList checkList, User user);
    boolean checkDone(CheckList checkList);
    List<CheckList> findAllCheckList(Long assetId);
    List<CheckList> findCheckListsByAssetId(Long assetId);
}

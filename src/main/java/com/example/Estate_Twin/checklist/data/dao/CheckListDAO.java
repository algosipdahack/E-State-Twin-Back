package com.example.Estate_Twin.checklist.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.time.LocalDateTime;

public interface CheckListDAO {
    CheckList saveCheckList(CheckList checkList, Asset asset);
    CheckList findCheckList(Long id);
    CheckList updateCheckList(Long id, String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                              Category category, String checkListContent, LocalDateTime repairDate,
                              RepairType repairType, String manufacturer);
    CheckList addCheckListMedia(Long id, Media media);
    void clearMedia(CheckList checkList);
}

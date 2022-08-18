package com.example.Estate_Twin.checklist.data.dao;

import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;

import java.time.LocalDateTime;

public interface CheckListDAO {
    CheckList saveCheckList(CheckList checkList);
    CheckList findCheckList(Long id);
    CheckList updateCheckList(Long id, String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                              Category category, String checkListContent, LocalDateTime repairDate,
                              RepairType repairType, String manufacturer);
}

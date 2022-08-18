package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class CheckListDAOImpl implements CheckListDAO {
    private CheckListRepository checkListRepository;

    @Override
    public CheckList saveCheckList(CheckList checkList) {
        return checkListRepository.save(checkList);
    }

    @Override
    public CheckList findCheckList(Long id) {
        return checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
    }

    @Override
    public CheckList updateCheckList(Long id, String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN, Category category, String checkListContent, LocalDateTime repairDate, RepairType repairType, String manufacturer) {
        CheckList newCheckList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id))
                .builder()
                .flawPart(flawPart)
                .brokerConfirmYN(brokerConfirmYN)
                .ownerConfirmYN(ownerConfirmYN)
                .category(category)
                .checkListContent(checkListContent)
                .repairDate(repairDate)
                .repairType(repairType)
                .manufacturer(manufacturer)
                .build();
        return checkListRepository.save(newCheckList);
    }
}

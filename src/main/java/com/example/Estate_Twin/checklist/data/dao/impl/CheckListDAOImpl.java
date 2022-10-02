package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class CheckListDAOImpl implements CheckListDAO {
    private CheckListRepository checkListRepository;
    private AssetRepository assetRepository;

    @Override
    public CheckList saveCheckList(CheckList checkList, Asset asset) {
        checkList.setAsset(asset);
        return checkListRepository.save(checkList);
    }

    @Override
    public CheckList findCheckList(Long id) {
        return checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
    }

    @Override
    public List<CheckList> findAllCheckList(Long assetId) {
        return assetRepository.findByIdUsingFetchJoin(assetId).orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+assetId)).getCheckLists();
    }

    @Override
    @Transactional
    public CheckList updateCheckList(Long id, CheckListUpdateRequestDto dto) {
       return findCheckList(id).update(dto);
    }
    @Transactional
    public CheckList confirmBroker(CheckList checkList) {
        return checkList.setBrokerConfirmY();
    }
    @Transactional
    public CheckList confirmUser(CheckList checkList, User user) {

        return checkList.setOwnerConfirmY();
    }
    public boolean checkUser(User user) {
        return true;
    }
}

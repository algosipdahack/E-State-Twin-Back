package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
@Transactional(readOnly = true)
public class CheckListDAOImpl implements CheckListDAO {
    private CheckListRepository checkListRepository;
    private AssetRepository assetRepository;

    @Override
    @Transactional
    public CheckList saveCheckList(CheckList checkList, Asset asset) {
        checkList.setAsset(asset);
        return checkListRepository.save(checkList);
    }
    @Override
    public List<CheckList> findCheckListsByAssetId(Long assetId) {
        return checkListRepository.findCheckListsByAsset_Id(assetId)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋아이디를 가진 체크리스트가 없습니다. id = "+assetId));
    }

    @Override
    public CheckList findCheckList(Long id) {
        return checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
    }

    @Override
    public List<CheckList> findAllCheckList(Long assetId) {
        return assetRepository.findByIdUsingFetchJoin(assetId)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+assetId)).getCheckLists();
    }

    @Override
    @Transactional
    public CheckList updateCheckList(Long id, CheckListUpdateRequestDto dto) {
       return findCheckList(id).update(dto);
    }

    @Override
    @Transactional
    public CheckList confirmBroker(CheckList checkList) {
        return checkList.setBrokerConfirmY();
    }

    @Override
    @Transactional
    public CheckList confirmUser(Long estateId, CheckList checkList, User user) {
        // 집주인인지 세입자인지 검증
        if(checkUser(estateId,user)) { // 집주인이면
            return checkList.setOwnerConfirmY();
        } else{ // 세입자라면
            return checkList.setTenantConfirmY();
        }
    }

    public boolean checkUser(Long estateId, User user) {
        if(user.getOwnEstates() != null && user.getOwnEstates().contains(estateId)) { //집주인이라면
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDone(CheckList checkList) {
        //모두 다 동의했을 경우
        if(checkList.getBrokerConfirmYN() && checkList.getOwnerConfirmYN() && checkList.getTenantConfirmYN()) {
            return true;
        }
        return false;
    }

}

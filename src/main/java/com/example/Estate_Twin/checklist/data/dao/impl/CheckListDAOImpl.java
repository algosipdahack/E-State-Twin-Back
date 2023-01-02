package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Transactional(readOnly = true)
@Log4j2
public class CheckListDAOImpl implements CheckListDAO {
    private CheckListRepository checkListRepository;
    private AssetRepository assetRepository;

    @Override
    @Transactional
    public CheckList saveCheckList(User user, CheckList checkList, Estate estate, Asset asset) {
        checkList.setTenantConfirmY();
        // 집주인이면
        if (estate.getOwner().getId() == user.getId()) {
            checkList.setOwnerConfirmY();
        }
        checkList.setAsset(asset);
        return checkListRepository.save(checkList);
    }

    @Override
    public List<CheckList> findCheckListsByAssetId(Long assetId) {
        return checkListRepository.findCheckListsByAsset_IdOrderByRepairDateDesc(assetId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ASSET_NOT_FOUND));
    }

    @Override
    public CheckList findCheckList(Long checkListId) {
        return checkListRepository.findById(checkListId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.CHECKLIST_NOT_FOUND));
    }
    @Override
    public List<CheckList> findAllCheckList(Long assetId) {
        return assetRepository.findByIdUsingFetchJoin(assetId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ASSET_NOT_FOUND)).getCheckLists();
    }
    @Override
    public CheckList findCheckListForUpdate(Long checkListId) {
        return checkListRepository.findByIdForUpdate(checkListId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.CHECKLIST_NOT_FOUND));
    }

    @Override
    @Transactional
    public CheckList updateCheckList(Long id, CheckListUpdateRequestDto dto) {
       return findCheckList(id).update(dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) //일반 트랜잭션으로 하면 try - catch가 롤백 마킹때문에 의도대로 작동하지 않음)
    public CheckList confirm(Long checkListId, User user) {
        CheckList checkList = findCheckListForUpdate(checkListId);
        checkList.setConfirmY(user);
        return checkList;
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

package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component
@Transactional(readOnly = true)
public class CheckListDAOImpl implements CheckListDAO {
    private CheckListRepository checkListRepository;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW) //일반 트랜잭션으로 하면 try - catch가 롤백 마킹때문에 의도대로 작동하지 않음)
    public CheckList confirm(Long checkListId, User user) {
        CheckList checkList = checkListRepository.findByIdForUpdate(checkListId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.CHECKLIST_NOT_FOUND));
        checkList.setConfirmY(user);
        return checkList;
    }

}

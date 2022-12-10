package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
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
                .orElseThrow(()->new IllegalArgumentException("해당 에셋아이디를 가진 체크리스트가 없습니다. id = "+assetId));
    }

    @Override
    public CheckList findCheckList(Long id) {
        return checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
    }
    public void test(Long checklistId, int waitingTime) throws InterruptedException {
        long start = System.currentTimeMillis();
        Optional<CheckList> option = checkListRepository.findByIdForUpdate(2L);
        if (!option.isPresent()) {
            throw new RuntimeException("포스트를 찾을 수 없습니다.");
        }
        log.info("포스트 조회에 걸린 시간: " + (System.currentTimeMillis() - start) + "ms");
        CheckList checkList = option.get();
        checkList.setContent("JPA는 어떤 방식으로 Pessimitic Lock을 제공하는지 정리하였습니다. " + Thread.currentThread().getName() + "에 의해 업데이트되었습니다.");
        checkListRepository.save(checkList);
        log.info(waitingTime + "ms 동안 대기합니다.");
        Thread.sleep(waitingTime);
    }
    @Override
    public List<CheckList> findAllCheckList(Long assetId) {
        return assetRepository.findByIdUsingFetchJoin(assetId)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+assetId)).getCheckLists();
    }
    @Override
    public CheckList findCheckListForUpdate(Long id) {
        return checkListRepository.findByIdForUpdate(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
    }

    @Override
    @Transactional
    public CheckList updateCheckList(Long id, CheckListUpdateRequestDto dto) {
       return findCheckList(id).update(dto);
    }

    @Override
    @Transactional
    public void confirmBroker(CheckList checkList) {
        checkList.setBrokerConfirmY();
    }

    @Override
    @Transactional
    public void confirmTotal(CheckList checkList) {
        checkList.setTotalConfirmY();
    }

    @Override
    @Transactional
    public void confirmOwner(CheckList checkList) {
        checkList.setOwnerConfirmY();
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

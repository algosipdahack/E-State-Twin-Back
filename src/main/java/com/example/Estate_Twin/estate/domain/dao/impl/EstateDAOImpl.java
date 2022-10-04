package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.Exception;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@AllArgsConstructor
public class EstateDAOImpl implements EstateDAO {
    private EstateRepository estateRepository;
    private EstateHitRepository estateHitRepository;
    private ContractStateRepository contractStateRepository;

    @Override
    @Transactional
    public Estate getEstate(Long id) {
        //조회수 증가
        return findEstate(id).updateEstateHit();
    }
    @Override
    public Estate saveFirst(Broker broker, User owner, Address address) {
        return estateRepository.save(new Estate(broker, owner, address));
    }

    @Override
    @Transactional
    public Estate saveEstate(Estate estate) {
        // 매물 등록중인 상태
        estate.setState(State.POST_DOING);

        //조회수 등록
        EstateHit estateHit = new EstateHit();
        estateHitRepository.save(estateHit);
        estate.setEstateHit(estateHit);

        // 새로운 상태 등록
        ContractState contractState = new ContractState().builder()
                .estate(estate)
                .state(State.POST_DOING)
                .build();
        contractStateRepository.save(contractState);

        return estate;
    }
    @Override
    public Estate findEstate(Long id) {
        return estateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id));
    }


    @Override
    public House findHouse(Long id) {
        return estateRepository.findHouse(id);
    }

    @Override
    public EstateHit findEstateHit(Long id) {
        return estateRepository.findEstateHit(id);
    }

    @Override
    public List<AssetResponseDto> findAssets(Long id) {
        return estateRepository.findAssetList(id);
    }

    @Override
    public List<EstateMainDto> findEstateCustomized(String borough) {
        return estateRepository.findByBoroughOrderByWeeklyHit(borough);
    }

    @Override
    public List<EstateListResponseDto> findAllEstateList() {
        return estateRepository.findEstateList();
    }

    @Override
    @Transactional
    public Estate updateEstate(Long id, EstateUpdateRequestDto dto) {
        return estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id))
                .update(dto);
    }

    @Override
    @Transactional
    public Estate allowBroker(Estate estate, Broker broker) {
        if (checkRoleBroker(estate, broker) == null) {
            throw new Exception("해당 매물의 broker가 아닙니다!");
        }
        return estate.setBrokerConfirmY();
    }

    @Override
    @Transactional
    public Estate allowOwner(Estate estate, User owner) {
        if (checkRoleOwner(estate, owner) == null) {
            throw new Exception("해당 매물의 owner가 아닙니다!");
        }
        return estate.setOwnerConfirmY();
    }
    // 해당 매물의 브로커인지 확인
    public Estate checkRoleBroker(Estate estate, Broker broker) {
        return broker.getTradeEstates().stream().filter(trade -> trade.equals(estate))
                .findAny().orElse(null);
    }
    //해당 매물의 소유주인지 확인
    public Estate checkRoleOwner(Estate estate, User owner) {
        return owner.getOwnEstates().stream().filter(own -> own.equals(estate))
                .findAny().orElse(null);
    }
    // 해당 매물이 올릴 수 있는 상태인지 확인
    @Transactional
    @Override
    public boolean checkEnroll(Estate estate) {
        if (estate.isOwnerConfirmYN() && estate.isBrokerConfirmYN()) {
            estate.setIsPosted();
            return true;
        }
        return false;
    }
}

package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class EstateDAOImpl implements EstateDAO {
    private EstateRepository estateRepository;
    private EstateHitRepository estateHitRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public Estate getEstate(Long id) {
        //조회수 증가
        estateHitRepository.findWithPessimisticLockById(id)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_HIT_NOT_FOUND))
                .updateHit();
        return estateRepository.findById(id)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));
    }

    @Override
    @Transactional
    public Estate saveFirst(Broker broker, User owner, Address address) {
        return estateRepository.save(new Estate(broker, owner, address));
    }

    @Override
    @Transactional
    public Estate saveEstate(Estate estate, House house) {
        //조회수 등록
        EstateHit estateHit = new EstateHit();
        estateHitRepository.save(estateHit);
        estate.setEstateHit(estateHit);

        estate.setHouse(house);

        return estate;
    }


    @Override
    @Transactional
    public Estate allowBroker(Estate estate, Broker broker) {
        if (!checkRoleBroker(estate, broker)) {
            throw new CheckHouseException(ErrorCode.USER_NOT_BROKER);
        }
        return estate.setBrokerConfirmY();
    }

    @Override
    @Transactional
    public Estate allowOwner(Estate estate, User owner) {
        if (!checkRoleOwner(estate, owner)) {
            throw new CheckHouseException(ErrorCode.USER_NOT_OWNER);
        }
        return estate.setOwnerConfirmY();
    }

    // 해당 매물의 브로커인지 확인
    public boolean checkRoleBroker(Estate estate, Broker broker) {
        return estate.getBroker().getId().equals(broker.getId());
    }

    //해당 매물의 소유주인지 확인
    public boolean checkRoleOwner(Estate estate, User owner) {
        return estate.getOwner().getId().equals(owner.getId());
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

    @Override
    @Transactional
    public Estate matchTenant(Long estateId, User user) {
        Estate estate = estateRepository.findById(estateId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));
        user.setTenantEstate(estate);
        userRepository.save(user);
        return estate;
    }
}

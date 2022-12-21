package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .orElseThrow(()->new IllegalArgumentException("해당 estateId를 가진 estateHit를 찾을 수 없습니다. id = "+id))
                .updateHit();
        return findEstate(id);
    }


    @Override
    public Broker findBrokerbyEstateId(Long estateId) {
        return estateRepository.findBrokerByEstate_Id(estateId)
                .orElseThrow(()->new IllegalArgumentException("해당 estateId를 가진 브로커를 찾을 수 없습니다. id = "+estateId));
    }

    @Override
    public List<Estate> findEstatesByBrokerId(Long brokerId) {
        return estateRepository.findEstatesByBroker_Id(brokerId)
                .orElseThrow(()->new IllegalArgumentException("해당 브로커아이디를 가진 매물을 찾을 수 없습니다. id = "+brokerId));

    }

    @Override
    public List<Estate> findEstatesByOwnerId(Long ownerId) {
        return estateRepository.findEstatesByOwner_Id(ownerId)
                .orElseThrow(()->new IllegalArgumentException("해당 오너아이디를 가진 매물을 찾을 수 없습니다. id = "+ownerId));

    }

    @Override
    public Estate findEstateByHouseId(Long houseId) {
        return estateRepository.findEstateByHouse_Id(houseId)
                .orElseThrow(()->new IllegalArgumentException("해당 하우스아이디를 가진 매물을 찾을 수 없습니다. id = "+houseId));

    }

    @Override
    public Estate findEstateByEstateHitId(Long estatehitId) {
        return estateRepository.findEstateByEstateHit_Id(estatehitId)
                .orElseThrow(()->new IllegalArgumentException("해당 estateHitId를 가진 매물을 찾을 수 없습니다. id = "+estatehitId));

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
    public List<EstateListResponseDto> findEstateListByBorough(String borough, Pageable pageable) {
        return estateRepository.findEstateByBorough(borough, pageable);
    }

    @Override
    public List<EstateListResponseDto> findEstateListByTown(String town, Pageable pageable) {
        return estateRepository.findEstateByTown(town, pageable);
    }


    @Override
    public Page<EstateListResponseDto> findAllEstateList(Long estateId, Pageable pageable) {
        return estateRepository.findEstateList(estateId, pageable);
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
        if (Boolean.FALSE.equals(checkRoleBroker(estate, broker))) {
            throw new CheckHouseException(ErrorCode.USER_NOT_BROKER);
        }
        return estate.setBrokerConfirmY();
    }

    @Override
    @Transactional
    public Estate allowOwner(Estate estate, User owner) {
        if (Boolean.FALSE.equals(checkRoleOwner(estate, owner))) {
            throw new CheckHouseException(ErrorCode.USER_NOT_OWNER);
        }
        return estate.setOwnerConfirmY();
    }

    // 해당 매물의 브로커인지 확인
    public Boolean checkRoleBroker(Estate estate, Broker broker) {
        return estate.getBroker().getId().equals(broker.getId());
    }

    //해당 매물의 소유주인지 확인
    public Boolean checkRoleOwner(Estate estate, User owner) {
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
        Estate estate = findEstate(estateId);
        userRepository.save(user.setTenantEstate(findEstate(estateId)));
        return estate;
    }

    @Override
    @Transactional
    public User updateBorough(User user, String borough) {
        return user.setBorough(borough);
    }
}

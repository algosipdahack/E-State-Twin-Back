package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Component
@AllArgsConstructor
public class EstateDAOImpl implements EstateDAO {
    private EstateRepository estateRepository;
    private EstateHitRepository estateHitRepository;
    private ContractStateRepository contractStateRepository;
    @Override
    public Estate saveFirst(Broker broker, User owner, Address address) {
        Estate estate = new Estate();
        estate.setBroker(broker);
        estate.setOwner(owner);
        estate.setAddress(address);
        return estateRepository.save(estate);
    }

    @Override
    public Estate saveEstate(Estate estate, House house, List<Asset> assets) {
        estate.setHouse(house);

        // 매물 등록중인 상태
        estate.setState(State.POST_DOING);

        EstateHit estateHit = new EstateHit();
        estateHitRepository.save(estateHit);
        estate.setEstateHit(estateHit);

        ContractState contractState = new ContractState().builder()
                .estate(estate)
                .state(State.POST_DOING)
                .build();
        contractStateRepository.save(contractState);

        return estateRepository.save(estate);
    }

    @Override
    public Estate findEstate(Long id) {
        Estate estate = estateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id));
        return estate;
    }

    @Override
    public AddressDto findAddress(Long id) {
        return estateRepository.findAddress(id);
    }

    @Override
    public HouseDto findHouse(Long id) {
        return estateRepository.findHouse(id);
    }

    @Override
    public EstateHitDto findEstateHit(Long id) {
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
    public Estate updateEstate(Long id, EstateUpdateRequestDto dto) {
        return estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id))
                .update(dto);
    }

    @Override
    public Estate allowBroker(Estate estate) {
        return estate.setBrokerConfirmY();
    }

    @Override
    public Estate allowOwner(Estate estate) {
        return estate.setOwnerConfirmY();

    }

    @Override
    public Estate enablePost(Estate estate) {
        return estate.setIsPosted();
    }
}

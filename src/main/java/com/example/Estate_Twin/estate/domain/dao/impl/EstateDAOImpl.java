package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EstateDAOImpl implements EstateDAO {
    private EstateRepository estateRepository;
    private EstateHitRepository estateHitRepository;
    private ContractStateRepository contractStateRepository;
    @Override
    public Estate saveEstate(Estate estate, House house, Address address, List<Asset> assets) {
        Estate newEstate = estateRepository.save(estate);
        newEstate.setHouse(house);
        newEstate.setAddress(address);

        EstateHit estateHit = new EstateHit();
        estateHitRepository.save(estateHit);
        newEstate.setEstateHit(estateHit);

        ContractState contractState = new ContractState();
        contractState.setEstate(newEstate);
        contractStateRepository.save(contractState);

        assets.forEach(asset -> {
            asset.setEstate(newEstate);
        });
        return newEstate;
    }

    @Override
    public Estate findEstate(Long id) {
        Estate estate = estateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id));

        EstateHit estateHit = estate.getEstateHit();
        //조회수 증가
        estateHit.updateHit();
        estateHitRepository.save(estateHit);
        return estate;
    }

    @Override
    public List<Estate> findEstateCustomized(String borough) {
        return estateRepository.findByBoroughOrderByWeeklyHit(borough);
    }

    @Override
    public List<Estate> findAllEstate() {
        return estateRepository.findAll();
    }

    @Override
    public void clearMedia(Estate estate) {
        estate.getEstateMedia().clear();
    }

    @Override
    public Estate updateEstate(Long id, String content, String model, TransactionType transactionType,
                               String estateThumbNail, String city, String borough,
                               String thumbNail3D) {
        Estate newEstate = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id))
                .builder()
                .content(content)
                .model(model)
                .transactionType(transactionType)
                .estateThumbNail(estateThumbNail)
                .city(city)
                .borough(borough)
                .thumbnail3D(thumbNail3D)
                .build();

        return estateRepository.save(newEstate);
    }

    @Override
    public Estate allowBroker(Estate estate) {
        estate.setBrokerConfirmY();
        return estateRepository.save(estate);
    }

    @Override
    public Estate allowOwner(Estate estate) {
        estate.setOwnerConfirmY();
        return estateRepository.save(estate);
    }

    @Override
    public Estate addEstateMedia(Long id, Media media) {
        Estate estate = findEstate(id);
        estate.addMedia(media);
        return estateRepository.save(estate);
    }
}

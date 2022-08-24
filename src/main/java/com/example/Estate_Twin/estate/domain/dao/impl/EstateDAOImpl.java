package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EstateDAOImpl implements EstateDAO {
    private EstateRepository estateRepository;
    @Override
    public Estate saveEstate(Estate estate, House house) {
        estate.setHouse(house);
        return estateRepository.save(estate);
    }

    @Override
    public Estate findEstate(Long id) {
        return estateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id));
    }

    @Override
    public Estate updateEstate(Long id, String content, String model, String arCam, ContractState contractState,
                               TransactionType transactionType, String estateThumbNail, String city, String borough,
                               String thumbNail3D) {
        Estate newEstate = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물을 찾을 수 없습니다. id = "+id))
                .builder()
                .content(content)
                .model(model)
                .arCam(arCam)
                .contractState(contractState)
                .transactionType(transactionType)
                .estateThumbNail(estateThumbNail)
                .city(city)
                .borough(borough)
                .thumbnail3D(thumbNail3D)
                .build();

        return estateRepository.save(newEstate);
    }

    @Override
    public Estate addEstateMedia(Long id, List<Media> mediaList) {
        Estate estate = findEstate(id);
        estate.addMedia(mediaList);
        return estateRepository.save(estate);
    }
}

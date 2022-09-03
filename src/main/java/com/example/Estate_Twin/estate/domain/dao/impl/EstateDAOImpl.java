package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class EstateDAOImpl implements EstateDAO {
    private EstateRepository estateRepository;
    private EstateHitRepository estateHitRepository;
    private ContractStateRepository contractStateRepository;
    @Override
    public Estate saveEstate(Estate estate, House house, Address address) {
        estate.setHouse(house);
        estate.setAddress(address);

        EstateHit estateHit = new EstateHit();
        estateHitRepository.save(estateHit);
        estate.setEstateHit(estateHit);

        ContractState contractState = new ContractState();
        contractState.setEstate(estate);
        contractStateRepository.save(contractState);

        return estateRepository.save(estate);
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
    public Estate addEstateMedia(Long id, List<Media> mediaList) {
        Estate estate = findEstate(id);
        estate.addMedia(mediaList);
        return estateRepository.save(estate);
    }
}

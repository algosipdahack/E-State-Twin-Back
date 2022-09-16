package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.estate.domain.dao.EstateHitDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EstateHitDAOImpl implements EstateHitDAO {
    private EstateHitRepository estateHitRepository;

    @Override
    public EstateHit updateHit(Estate estate) {
        EstateHit estateHit = estate.getEstateHit();
        estateHit.updateHit();
        return estateHitRepository.save(estateHit);
    }
}

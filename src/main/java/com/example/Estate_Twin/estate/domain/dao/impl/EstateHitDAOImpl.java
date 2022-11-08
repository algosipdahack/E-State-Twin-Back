package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.estate.domain.dao.EstateHitDAO;
import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class EstateHitDAOImpl implements EstateHitDAO {
    private EstateHitRepository estateHitRepository;

    @Override
    public EstateHit getEstateHit(Long estateId) {
        return estateHitRepository.findEstateHitByEstate_Id(estateId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 estate가 없습니다!!"+estateId));
    }
}

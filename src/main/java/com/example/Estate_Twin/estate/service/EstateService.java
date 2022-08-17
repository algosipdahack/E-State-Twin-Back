package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class EstateService {
    private final EstateRepository estateRepository;
    private final HouseRepository houseRepository;

    public EstateResponseDto findById(Long id) {
        Estate entity = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 매물이 없습니다. id = "+ id));

        return new EstateResponseDto(entity);
    }
    @Transactional
    public Long save(EstateSaveRequestDto estateSaveRequestDto, Long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(()-> new IllegalArgumentException("해당 집이 없습니다. id = "+ houseId));
        estateSaveRequestDto.setHouse(house);
        return estateRepository.save(estateSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, EstateUpdateRequestDto estateUpdateRequestDto) {
        Estate estate = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));

        estate.update(estateUpdateRequestDto.getTransactionType(),
                estateUpdateRequestDto.getEstateThumbNail(),estateUpdateRequestDto.getContent(),estateUpdateRequestDto.getRank(),
                estateUpdateRequestDto.getModel(),estateUpdateRequestDto.getArCam(),estateUpdateRequestDto.getCity(),
                estateUpdateRequestDto.getBorough(),estateUpdateRequestDto.getAddress()
        );
        return id;
    }

}

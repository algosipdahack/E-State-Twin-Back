package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class EstateService {
    private final EstateRepository estateRepository;
    public EstateResponseDto findById(Long id) {
        Estate entity = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        return new EstateResponseDto(entity);
    }
    @Transactional
    public Long save(EstateSaveRequestDto estateSaveRequestDto) {
        return estateRepository.save(estateSaveRequestDto.toEntity()).getEstateId();
    }

    @Transactional
    public Long update(Long id, EstateUpdateRequestDto estateUpdateRequestDto) {
        Estate entity = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        entity.update(estateUpdateRequestDto.getBroker(),estateUpdateRequestDto.getEstateMedia(),estateUpdateRequestDto.getConstractState(),
                estateUpdateRequestDto.getEstateHit(),estateUpdateRequestDto.getTransactionType(),estateUpdateRequestDto.getEstateThumbNail(),
                estateUpdateRequestDto.getContent(),estateUpdateRequestDto.getRank(),estateUpdateRequestDto.getModel(),
                estateUpdateRequestDto.getArCam(),estateUpdateRequestDto.getCity(),estateUpdateRequestDto.getAd_distinct(),
                estateUpdateRequestDto.getAddress()
        );
        return id;
    }

}

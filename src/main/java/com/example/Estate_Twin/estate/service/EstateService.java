package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.constractstate.domain.repository.ConstactStateRepository;
import com.example.Estate_Twin.constractstate.service.ConstractStateService;
import com.example.Estate_Twin.constractstate.web.dto.ConstractStateSaveRequestDto;
import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Service
public class EstateService {
    private final EstateRepository estateRepository;
    private final ConstractStateService constractStateService;

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
    public Long update(Long id, EstateUpdateRequestDto estateUpdateRequestDto, String transactionType) {
        Estate estate = estateRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));

        estate.update(transactionType,
                estateUpdateRequestDto.getEstateThumbNail(),estateUpdateRequestDto.getContent(),estateUpdateRequestDto.getRank(),
                estateUpdateRequestDto.getModel(),estateUpdateRequestDto.getArCam(),estateUpdateRequestDto.getCity(),
                estateUpdateRequestDto.getAd_distinct(),estateUpdateRequestDto.getAddress()
        );
        return id;
    }

}

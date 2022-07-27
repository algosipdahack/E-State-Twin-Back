package com.example.Estate_Twin.service.Estate;

import com.example.Estate_Twin.domain.estate.EstateRepository;
import com.example.Estate_Twin.web.dto.EstateSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class EstateService {
    private final EstateRepository estateRepository;

    @Transactional
    public Long save(EstateSaveRequestDto requestDto) {

        return estateRepository.save(requestDto.toEntity()).getId();
    }
}

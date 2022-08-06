package com.example.Estate_Twin.constractstate.service;

import com.example.Estate_Twin.constractstate.domain.repository.ConstactStateRepository;
import com.example.Estate_Twin.constractstate.web.dto.ConstractStateSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ConstractStateService {
    private final ConstactStateRepository constactStateRepository;


    @Transactional
    public Long save(ConstractStateSaveRequestDto constractStateSaveRequestDto) {
        return constactStateRepository.save(constractStateSaveRequestDto.toEntity()).getId();
    }
}

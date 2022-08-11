package com.example.Estate_Twin.contractstate.web;

import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/constractState")
public class ContractStateController {
    private final ContractStateService contractStateService;

    @GetMapping("/{constractStateId}")
    public ContractStateResponseDto getConstractState(@PathVariable Long constractStateId) {
        return contractStateService.findById(constractStateId);
    }

    @PostMapping("")
    public Long saveConstractState(@RequestBody ContractStateSaveRequestDto contractStateSaveRequestDto) {
        return contractStateService.save(contractStateSaveRequestDto);
    }

    @PutMapping("/{constractStateId}")
    public Long updateConstractState(@PathVariable Long constractStateId, @RequestBody String state){
        return contractStateService.update(constractStateId, state);
    }
}

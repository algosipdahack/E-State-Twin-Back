package com.example.Estate_Twin.contractstate.web;

import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contractState")
public class ContractStateController {
    private final ContractStateService contractStateService;

    @GetMapping("/{contractStateId}")
    public ContractStateResponseDto getContractState(@PathVariable Long contractStateId) {
        return contractStateService.findById(contractStateId);
    }

    @PostMapping("/{estateId}")
    public Long saveContractState(@PathVariable Long estateId, @RequestBody ContractStateSaveRequestDto contractStateSaveRequestDto) {
        return contractStateService.save(estateId, contractStateSaveRequestDto);
    }

    @PutMapping("/{contractStateId}")
    public Long updateContractState(@PathVariable Long contractStateId, @RequestBody String state){
        return contractStateService.update(contractStateId, state);
    }
}

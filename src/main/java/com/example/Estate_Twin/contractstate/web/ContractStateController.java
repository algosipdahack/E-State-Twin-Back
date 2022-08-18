package com.example.Estate_Twin.contractstate.web;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contractState")
public class ContractStateController {
    private final ContractStateService contractStateService;

    @GetMapping("/{contractStateId}")
    public ResponseEntity<ContractStateResponseDto> getContractState(@PathVariable Long contractStateId) {
        ContractStateResponseDto contractStateResponseDto = contractStateService.getContractState(contractStateId);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    @PostMapping("/{estateId}")
    public ResponseEntity<ContractStateResponseDto> saveContractState(@PathVariable Long estateId, @RequestBody ContractStateSaveRequestDto contractStateSaveRequestDto) {
        ContractStateResponseDto contractStateResponseDto = contractStateService.saveContractState(estateId,contractStateSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    @PutMapping("/{contractStateId}")
    public ResponseEntity<ContractStateResponseDto> updateContractState(@PathVariable Long contractStateId, @RequestBody String state){
        ContractStateResponseDto contractStateResponseDto = contractStateService.updateState(contractStateId,State.of(state));
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }
}

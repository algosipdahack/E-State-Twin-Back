package com.example.Estate_Twin.contractstate.web;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.service.ContractStateService;
import com.example.Estate_Twin.contractstate.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ContractState", description = "매물상태 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contractState")
public class ContractStateController {
    private final ContractStateService contractStateService;

    @Operation(summary = "get ContractState", description = "매물 상태에 대한 정보들 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "contractStateId", description = "ContractState Id", example = "1")
    })
    @GetMapping("/{contractStateId}")
    public ResponseEntity<ContractStateResponseDto> getContractState(@PathVariable Long contractStateId) {
        ContractStateResponseDto contractStateResponseDto = contractStateService.getContractState(contractStateId);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    @Operation(summary = "post ContractState", description = "매물 상태 등록하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @PostMapping("/{estateId}")
    public ResponseEntity<ContractStateResponseDto> saveContractState(@PathVariable Long estateId, @RequestBody ContractStateSaveRequestDto contractStateSaveRequestDto) {
        ContractStateResponseDto contractStateResponseDto = contractStateService.saveContractState(estateId,contractStateSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    @Operation(summary = "update ContractState", description = "매물 상태 수정하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "contractStateId", description = "ContractState Id", example = "1")
    })
    @PutMapping("/{contractStateId}")
    public ResponseEntity<ContractStateResponseDto> updateContractState(@PathVariable Long contractStateId, @RequestBody String state){
        ContractStateResponseDto contractStateResponseDto = contractStateService.updateState(contractStateId,State.of(state));
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }
}

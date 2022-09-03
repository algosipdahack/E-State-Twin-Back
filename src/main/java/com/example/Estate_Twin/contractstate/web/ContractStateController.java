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

import java.util.List;

@Tag(name = "ContractState", description = "매물상태 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contractState")
public class ContractStateController {
    private final ContractStateService contractStateService;

    @Operation(summary = "update ContractState", description = "매물 상태 수정하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "contractStateId", description = "ContractState Id", example = "1")
    })
    @PutMapping("/{estateId}")
    public ResponseEntity<ContractStateResponseDto> updateContractState(@PathVariable Long estateId, @RequestBody ContractStateUpdateRequestDto contractStateUpdateRequestDto){
        ContractStateResponseDto contractStateResponseDto = contractStateService.updateState(estateId,contractStateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    @Operation(summary = "get ContractState", description = "매물에 따른 상태 정보들 리스트로 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @GetMapping("/{estateId}")
    public ResponseEntity<List<ContractStateResponseDto>> getContractStateList(@PathVariable Long estateId) {
        List<ContractStateResponseDto> contractStateResponseDtos = contractStateService.getContractState(estateId);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDtos);
    }
}

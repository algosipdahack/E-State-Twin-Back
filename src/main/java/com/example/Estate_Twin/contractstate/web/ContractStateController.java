package com.example.Estate_Twin.contractstate.web;

import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.service.impl.ContractStateServiceImpl;
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
    private final ContractStateServiceImpl contractStateService;

    @Operation(summary = "update ContractState", description = "매물 상태 수정하기 ex) 브로커가 매물 등록 승인 한 상황 : state = POST_DOING 요청")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("/estate/{estateId}")
    public ResponseEntity<ContractStateResponseDto> updateContractState(@PathVariable Long estateId, @RequestBody State state){
        ContractStateResponseDto contractStateResponseDto = contractStateService.updateState(estateId,state);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    @Operation(summary = "get ContractState", description = "매물에 따른 상태 정보들 리스트로 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ContractStateResponseDto.class)))})})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @GetMapping("/estate/{estateId}")
    public ResponseEntity<List<ContractStateResponseDto>> getContractStateList(@PathVariable Long estateId) {
        List<ContractStateResponseDto> contractStateResponseDtos = contractStateService.getContractState(estateId);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDtos);
    }

}

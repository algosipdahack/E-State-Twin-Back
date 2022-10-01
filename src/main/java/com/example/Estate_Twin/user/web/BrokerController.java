package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.service.impl.BrokerServiceImpl;
import com.example.Estate_Twin.user.web.dto.*;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Broker", description = "브로커 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/broker")
public class BrokerController {
    private final BrokerServiceImpl brokerService;

    @Operation(summary = "mypage of broker", description = "브로커 마이페이지")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))})
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BrokerResponseDto> getCurrentBroker(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        BrokerResponseDto brokerResponseDto = brokerService.getBroker(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(brokerResponseDto);
    }

    @Operation(summary = "signup of broker", description = "브로커 회원가입")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))})
    @PostMapping("/signup")
    public ResponseEntity<BrokerResponseDto> signup(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @RequestBody BrokerSignUpDto brokerSignUpDto) {
        BrokerResponseDto brokerResponseDto = brokerService.signUpBroker(user.getEmail(), brokerSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(brokerResponseDto);
    }


    @Operation(summary = "show broker list", description = "매물의 거리에 따른 공인중개사 리스트 보여주기(전체 다 넘겨줌)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))})
    @GetMapping("/list")
    public ResponseEntity<List<BrokerListDto>> getBrokerList() {
        List<BrokerListDto> brokerListDto = brokerService.getBrokerList();
        return ResponseEntity.status(HttpStatus.OK).body(brokerListDto);
    }


    @Operation(summary = "show EstateList based on the state of estate(broker)", description = "매물 등록 상태에 따른 매물 리스트 보여주기(broker)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerEstateDto.class)))})
    @GetMapping("/estate")
    public ResponseEntity<List<BrokerEstateDto>> getEstate(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user,
                                                           @ApiParam(value = "State", required = true, example = "BROKER_BEFORE, POST_DOING, POST_DONE, CONTRACT_REQUEST, CONFIRM_BROKER, CONFIRM_OWNER, CHECKLIST_DOING, CONTRACT_DONE")
                                                            @RequestParam(name = "State") String state) {
        List<BrokerEstateDto> brokerEstateDtos = brokerService.getbrokerEstate(user.getEmail(), State.of(state));
        return ResponseEntity.status(HttpStatus.OK).body(brokerEstateDtos);
    }

}

package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Broker", description = "브로커 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/broker")
public class BrokerController {
    private final BrokerService brokerService;

    @Operation(summary = "mypage of broker", description = "브로커 마이페이지")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))})
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BrokerResponseDto> getCurrentBroker(@AuthenticationPrincipal CustomUserDetails user) {
        BrokerResponseDto brokerResponseDto = brokerService.getBroker(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(brokerResponseDto);
    }

    @Operation(summary = "signup of broker", description = "브로커 회원가입")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))})
    @PostMapping("/signup")
    public ResponseEntity<BrokerResponseDto> signup(@AuthenticationPrincipal CustomUserDetails user, BrokerSignUpDto brokerSignUpDto) {
        BrokerResponseDto brokerResponseDto = brokerService.signUpBroker(user.getEmail(),brokerSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(brokerResponseDto);
    }

    //TODO 공인중개사가 가진 매물 보여주기
    @Operation(summary = "show broker trade estate", description = "브로커가 거래중인 매물 보여주기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))})
    @GetMapping("/estate")
    public ResponseEntity<BrokerResponseDto> showEstate(@AuthenticationPrincipal CustomUserDetails user, BrokerSignUpDto brokerSignUpDto) {
        BrokerResponseDto brokerResponseDto = brokerService.signUpBroker(user.getEmail(),brokerSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(brokerResponseDto);
    }

    //TODO 공인중개사 리스트 보여주기(borough에 따른)
    //중개소 목록 보여줌
    //중개소 이름
    //소개글
    //거래완료 건수
    //대표전화번호

}

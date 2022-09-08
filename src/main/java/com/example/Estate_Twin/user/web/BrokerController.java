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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))
    })
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BrokerResponseDto> getCurrentBroker(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(brokerService.getBroker(user.getId()));
    }

    @Operation(summary = "signup of broker", description = "브로커 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerResponseDto.class)))
    })
    @GetMapping("/signup")
    public ResponseEntity<BrokerResponseDto> signup(@AuthenticationPrincipal CustomUserDetails user, BrokerSignUpDto brokerSignUpDto) {
        return ResponseEntity.status(HttpStatus.OK).body(brokerService.signUpBroker(user.getId(),brokerSignUpDto));
    }
}

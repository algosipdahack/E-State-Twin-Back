package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.BrokerResponseDto;
import com.example.Estate_Twin.user.web.dto.BrokerSignUpDto;
import com.example.Estate_Twin.user.web.dto.UserResponseDto;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Broker", description = "브로커 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/broker")
public class BrokerController {
    private final BrokerService brokerService;

    @Operation(summary = "signup of user", description = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    })
    @GetMapping("/signup")
    public ResponseEntity<BrokerResponseDto> signup(@AuthenticationPrincipal CustomUserDetails user, BrokerSignUpDto brokerSignUpDto) {
        return ResponseEntity.status(HttpStatus.OK).body(brokerService.signUpBroker(user.getId(),brokerSignUpDto));
    }
}

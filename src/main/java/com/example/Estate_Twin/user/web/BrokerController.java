package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.impl.BrokerServiceImpl;
import com.example.Estate_Twin.user.web.dto.*;
import com.example.Estate_Twin.util.CurrentUser;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Broker", description = "브로커 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/broker")
public class BrokerController {
    private final BrokerServiceImpl brokerService;

    @Operation(summary = "My Page of Broker", description = "브로커 마이페이지")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BrokerSummaryDto.class)))
    @GetMapping("/me")
    public ResponseEntity<BrokerSummaryDto> getCurrentBroker(@Parameter(hidden = true) @CurrentUser User user) {
        BrokerSummaryDto brokerResponseDto = brokerService.getBrokerWithUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(brokerResponseDto);
    }

    @Operation(summary = "Signup of Broker", description = "브로커 회원가입")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = BrokerSummaryDto.class)))
    @PostMapping("/signup")
    public ResponseEntity<BrokerSummaryDto> signup(@Parameter(hidden = true) @CurrentUser User user,
                                                   @RequestBody BrokerSignUpDto brokerSignUpDto) {
        BrokerSummaryDto brokerResponseDto = brokerService.signUpBroker(user, brokerSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(brokerResponseDto);
    }

    @Operation(summary = "Show Broker List", description = "매물의 거리에 따른 공인중개사 리스트 보여주기(전체 다 넘겨줌)")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BrokerListDto.class)))})})
    @GetMapping("/list")
    public ResponseEntity<List<BrokerListDto>> getBrokerList() {
        List<BrokerListDto> brokerListDto = brokerService.getBrokerList();
        return ResponseEntity.status(HttpStatus.OK).body(brokerListDto);
    }

    @Operation(summary = "Show Estate List Based on the State of Estate(broker)", description = "매물 등록 상태에 따른 매물 리스트 보여주기(broker)")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BrokerEstateDto.class)))})})
    @GetMapping("/estates")
    public ResponseEntity<List<BrokerEstateDto>> getEstate(@Parameter(hidden = true) @CurrentUser User user,
                                                           @ApiParam(value = "state", required = true, example = "BROKER_BEFORE, POST_DOING, POST_DONE, CONTRACT_REQUEST, CONFIRM_BROKER, CONFIRM_OWNER, CHECKLIST_DOING, CONTRACT_DONE")
                                                            @RequestParam(name = "state") String state) {
        List<BrokerEstateDto> brokerEstateDtos = brokerService.getBrokerEstate(user.getEmail(), State.of(state));
        return ResponseEntity.status(HttpStatus.OK).body(brokerEstateDtos);
    }

}

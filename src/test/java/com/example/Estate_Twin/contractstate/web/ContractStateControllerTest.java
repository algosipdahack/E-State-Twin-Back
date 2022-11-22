package com.example.Estate_Twin.contractstate.web;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.service.impl.ContractStateServiceImpl;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.OAuthService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ContractStateController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class ContractStateControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    ContractStateServiceImpl contractStateService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    ContractState contractState;
    Estate estate;
    Address address;
    Broker broker;
    User user;
    @BeforeEach
    void setting() {
        String date = "2022-10-22";
        user = User.builder()
                .borough("강남구")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("조소연")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        address = Address.builder()
                .unit("5호")
                .town("화정동")
                .subBuildingNumber(1234)
                .mainBuildingNumber(2345)
                .complexName("벽산아파트")
                .buildingName("빌딩빌딩")
                .roadName("화정로")
                .city("고양시")
                .borough("화정동")
                .block("623동")
                .build();
        broker = Broker.builder()
                .content("내용")
                .brokerPhoto("broker_photo")
                .brokerageRegistrationLicense("registration_license")
                .businessLicense("business_license")
                .address(address)
                .brokerageRegistrationNumber("brokerage_number")
                .agentName("000공인중개사")
                .businessName("000공인중개소")
                .businessRegistrationNumber("business_number")
                .build();
        estate = Estate.builder()
                .address(address)
                .broker(broker)
                .owner(user)
                .build();
        contractState = ContractState.builder()
                .state(State.BROKER_BEFORE)
                .estate(estate)
                .build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @DisplayName("[get] /api/contractState/estate/{estateId}")
    @Test
    @WithMockCustomUser
    void 계약상태_가져오기() throws Exception{
        List<ContractStateResponseDto> contractStateResponseDtos = new ArrayList<>();
        ContractStateResponseDto contractStateResponseDto = ContractStateResponseDto.builder()
                .contractState(contractState)
                .build();
        contractStateResponseDtos.add(contractStateResponseDto);

        given(contractStateService.getContractState(any()))
                .willReturn(contractStateResponseDtos);
        Long estateId = 1L;
        //when
        mockMvc.perform(get("/api/contractState/estate/{estateId}",estateId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[put] /api/contractState/estate/{estateId}")
    @Test
    @WithMockCustomUser
   void 계약상태_수정하기() throws Exception{
        Long estateId = 1L;
        estate.setState(State.CONTRACT_DONE);
        ContractStateResponseDto contractStateResponseDto = ContractStateResponseDto.builder()
                .contractState(new ContractState(State.CONTRACT_DONE, estate))
                .build();
        //given
        given(contractStateService.updateState(any(), any()))
                .willReturn(contractStateResponseDto);

        //when
        mockMvc.perform(put("/api/contractState/estate/{estateId}",estateId)
                        .param("state", "CONTRACT_DONE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("CONTRACT_DONE"));
    }

}
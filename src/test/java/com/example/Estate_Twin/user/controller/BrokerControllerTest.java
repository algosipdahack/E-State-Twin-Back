package com.example.Estate_Twin.user.controller;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.*;
import com.example.Estate_Twin.user.web.BrokerController;
import com.example.Estate_Twin.user.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {BrokerController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class BrokerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    BrokerServiceImpl brokerService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    User user;
    Asset asset;
    CheckList checkList;
    Broker broker;
    Address address;
    @BeforeEach
    void setting() {
        String date = "2022-10-22";
        user = User.builder()
                .borough("?????????")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("?????????")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        asset = Asset.builder()
                .assetPhoto("asset_photo")
                .anchorId("anchor_id")
                .manufacturer("LG ??????")
                .productName("?????????")
                .category(Category.AIRCONDITIONER)
                .build();
        checkList = CheckList.builder()
                .checkListPhoto("checklist_photo")
                .repairType(RepairType.REPAIR)
                .checkListContent("content")
                .flawPart("????????????")
                .repairDate(LocalDate.of(2022,06,12))
                .build();
        address = Address.builder()
                .unit("5???")
                .town("?????????")
                .subBuildingNumber(1234)
                .mainBuildingNumber(2345)
                .complexName("???????????????")
                .buildingName("????????????")
                .roadName("?????????")
                .city("?????????")
                .borough("?????????")
                .block("623???")
                .build();

        broker = Broker.builder()
                .content("??????")
                .brokerPhoto("broker_photo")
                .brokerageRegistrationLicense("registration_license")
                .businessLicense("business_license")
                .address(address)
                .brokerageRegistrationNumber("brokerage_number")
                .agentName("000???????????????")
                .businessName("000???????????????")
                .businessRegistrationNumber("business_number")
                .build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("[get] /api/user/me")
    @Test
    @WithMockCustomUser
    void ???????????????() throws Exception{
        BrokerSummaryDto brokerSummaryDto = BrokerSummaryDto.builder()
                .broker(broker)
                .build();

        //given
        given(brokerService.getBrokerWithUser(any()))
                .willReturn(brokerSummaryDto);

        //when
        mockMvc.perform(get("/api/broker/me"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[post] /api/broker/signup")
    @Test
    @WithMockCustomUser
    void ????????????() throws Exception{
        BrokerSummaryDto brokerSummaryDto = BrokerSummaryDto.builder()
                .broker(broker)
                .build();
        BrokerSignUpDto brokerSignUpDto = new BrokerSignUpDto("000???????????????","000???????????????","brokerage_number","business_license","business_license","registration_license","broker_photo","??????",address);

        //given
        given(brokerService.signUpBroker(any(), any()))
                .willReturn(brokerSummaryDto);

        String content = objectMapper.writeValueAsString(brokerSignUpDto);
        //when
        mockMvc.perform(post("/api/broker/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("[get] /api/broker/list")
    @Test
    @WithMockCustomUser
    void ?????????_?????????() throws Exception{
        List<BrokerListDto> brokerListDtoList = new ArrayList<>();
        BrokerListDto brokerListDto = BrokerListDto.builder()
                .brokerPhoto(broker.getBrokerPhoto())
                .address(address)
                .id(broker.getId())
                .businessName(broker.getBusinessName())
                .content(broker.getContent())
                .countOfTransactionCompletion(broker.getCountOfTransactionCompletion())
                .build();
        brokerListDtoList.add(brokerListDto);

        //given
        given(brokerService.getBrokerList())
                .willReturn(brokerListDtoList);

        //when
        mockMvc.perform(get("/api/broker/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(broker.getId()));
    }

    @DisplayName("[get] /api/broker/estate")
    @Test
    @WithMockCustomUser
    void ?????????_??????_?????????() throws Exception{
        List<BrokerEstateDto> brokerEstateDtos = new ArrayList<>();
        BrokerEstateDto brokerEstateDto = BrokerEstateDto.builder()
                .estateAddress(address)
                .estateId(1L)
                .ownerName(user.getName())
                .ownerPhone(user.getPhone())
                .build();
        brokerEstateDtos.add(brokerEstateDto);

        //given
        given(brokerService.getBrokerEstate(any(),any()))
                .willReturn(brokerEstateDtos);

        //when
        mockMvc.perform(get("/api/broker/estate")
                        .param("state", "BROKER_BEFORE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].estateId").value(1L));
    }
}

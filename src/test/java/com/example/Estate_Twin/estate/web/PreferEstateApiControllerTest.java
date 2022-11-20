package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.service.impl.PreferEstateServiceImpl;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.impl.OAuthService;
import com.example.Estate_Twin.user.service.impl.UserServiceImpl;
import com.example.Estate_Twin.user.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {UserController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class PreferEstateApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    PreferEstateServiceImpl preferEstateService;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private ObjectMapper objectMapper;

    Asset asset;
    CheckList checkList;
    Estate estate;
    User user;
    Broker broker;
    Address address;
    HouseUpdateRequestDto house;
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

        asset = Asset.builder()
                .assetPhoto("asset_photo")
                .anchorId("anchor_id")
                .manufacturer("LG 가전")
                .productName("냉장고")
                .category(Category.BATHROOM)
                .option(Option.AIRCONDITIONER)
                .build();

        checkList = CheckList.builder()
                .checkListPhoto("checklist_photo")
                .repairType(RepairType.REPAIR)
                .checkListContent("content")
                .ownerConfirmYN(false)
                .brokerConfirmYN(false)
                .flawPart("하자부위")
                .repairDate(LocalDate.of(2022,06,12))
                .tenantConfirmYN(false)
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
        house =  new HouseUpdateRequestDto(1L,1L,1L,1L,1L,false,1L,"fee",1L,1L,false,1L,LocalDate.now(),"heattype",EstateType.OFFICETELS,false,false,"LOFT",true);
        EstateUpdateRequestDto estateUpdateRequestDto = new EstateUpdateRequestDto("MONTHLYRENT","thumbnail","content","model", Arrays.asList("photo"),address,house);
        estate.update(estateUpdateRequestDto);
    }
    @DisplayName("[get] /api/preferestate/list/recent")
    @Test
    @WithMockCustomUser
    void 최근본방_가져오기() throws Exception{
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        EstateListResponseDto estateListResponseDto = new EstateListResponseDto(estate.getId(),estate.getTransactionType(),estate.getEstateThumbNail(),
                estate.getAddress().getTown(),house.getEstateType(),
                estate.getAddress().getBuildingName(),house.getCurrentFloors(),house.getRentableArea(),estate.getState(),house.getSellingFee());
        estateListResponseDtos.add(estateListResponseDto);


        //given
        given(preferEstateService.getPreferEstate(any(),any(),any()))
                .willReturn((Page<EstateListResponseDto>) estateListResponseDtos);

        //when
        mockMvc.perform(get("/api/preferestate/list/recent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].estateType").value(house.getEstateType()));
    }
}
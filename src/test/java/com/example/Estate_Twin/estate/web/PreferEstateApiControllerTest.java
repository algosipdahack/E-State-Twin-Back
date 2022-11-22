package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.service.impl.PreferEstateServiceImpl;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.*;
import com.example.Estate_Twin.user.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
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
                .flawPart("하자부위")
                .repairDate(LocalDate.of(2022,06,12))
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
        house =  new HouseUpdateRequestDto(1L,1L,1L,1L,1L,false,1L,"fee",1L,1L,false,1L,LocalDate.now(),"heattype","OFFICETELS",false,false,"LOFT",true);
        EstateUpdateRequestDto estateUpdateRequestDto = new EstateUpdateRequestDto("MONTHLYRENT","thumbnail","content","model", Arrays.asList("photo"),address,house);
        estate.update(estateUpdateRequestDto);
        this.mockMvc= MockMvcBuilders.standaloneSetup(new PreferEstateApiController(preferEstateService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }
    @DisplayName("[get] /api/preferestate/list/recent")
    @Test
    @WithMockCustomUser
    void 최근본방_가져오기() throws Exception{
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        EstateListResponseDto estateListResponseDto = new EstateListResponseDto(estate.getId(),estate.getTransactionType(),estate.getEstateThumbNail(),
                estate.getAddress().getTown(),EstateType.of(house.getEstateType()),
                estate.getAddress().getBuildingName(),house.getCurrentFloors(),house.getRentableArea(),estate.getState(),house.getSellingFee());
        estateListResponseDtos.add(estateListResponseDto);
        Page<EstateListResponseDto> estateListResponsePageDtos = new PageImpl<EstateListResponseDto>(estateListResponseDtos, PageRequest.of(0, 3, Sort.unsorted()), 5);

        //given
        given(preferEstateService.getPreferEstate(any(),eq(Preference.RECENT),any()))
                .willReturn((estateListResponsePageDtos));

        //when
        mockMvc.perform(get("/api/preferestate/list/recent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[get] /api/preferestate/list/dip")
    @Test
    @WithMockCustomUser
    void 찜한방_가져오기() throws Exception{
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        EstateListResponseDto estateListResponseDto = new EstateListResponseDto(estate.getId(),estate.getTransactionType(),estate.getEstateThumbNail(),
                estate.getAddress().getTown(),EstateType.of(house.getEstateType()),
                estate.getAddress().getBuildingName(),house.getCurrentFloors(),house.getRentableArea(),estate.getState(),house.getSellingFee());
        estateListResponseDtos.add(estateListResponseDto);
        Page<EstateListResponseDto> estateListResponsePageDtos = new PageImpl<EstateListResponseDto>(estateListResponseDtos, PageRequest.of(0, 3, Sort.unsorted()), 5);

        //given
        given(preferEstateService.getPreferEstate(any(),eq(Preference.DIP),any()))
                .willReturn((estateListResponsePageDtos));

        //when
        mockMvc.perform(get("/api/preferestate/list/dip"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[get] /api/preferestate/list/inquiry")
    @Test
    @WithMockCustomUser
    void 문의한방_가져오기() throws Exception{
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        EstateListResponseDto estateListResponseDto = new EstateListResponseDto(estate.getId(),estate.getTransactionType(),estate.getEstateThumbNail(),
                estate.getAddress().getTown(),EstateType.of(house.getEstateType()),
                estate.getAddress().getBuildingName(),house.getCurrentFloors(),house.getRentableArea(),estate.getState(),house.getSellingFee());
        estateListResponseDtos.add(estateListResponseDto);
        Page<EstateListResponseDto> estateListResponsePageDtos = new PageImpl<EstateListResponseDto>(estateListResponseDtos, PageRequest.of(0, 3, Sort.unsorted()), 5);

        //given
        given(preferEstateService.getPreferEstate(any(),eq(Preference.INQUIRY),any()))
                .willReturn((estateListResponsePageDtos));

        //when
        mockMvc.perform(get("/api/preferestate/list/inquiry"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
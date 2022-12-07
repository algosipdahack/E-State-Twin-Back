package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.*;
import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.service.impl.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.OAuthService;
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
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {EstateApiController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class EstateApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    EstateServiceImpl estateService;
    @MockBean
    PreferEstateServiceImpl preferEstateService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    Estate estate;
    User user;
    Broker broker;
    Address address;
    Asset asset;
    HouseUpdateRequestDto house;
    EstateUpdateRequestDto estateUpdateRequestDto;
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
        asset = Asset.builder()
                .assetPhoto("asset_photo")
                .anchorId("anchor_id")
                .manufacturer("LG 가전")
                .productName("냉장고")
                .category(Category.BATHROOM)
                .option(Household.AIRCONDITIONER)
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
        estateUpdateRequestDto = new EstateUpdateRequestDto("MONTHLYRENT","thumbnail","content","model", Arrays.asList("photo"),address,house);
        estate.update(estateUpdateRequestDto);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(new EstateApiController(estateService,preferEstateService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @DisplayName("[get] /api/estate/list")
    @Test
    @WithMockCustomUser
    void 매물_리스트() throws Exception{
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        EstateListResponseDto estateListResponseDto = new EstateListResponseDto(estate.getId(),estate.getTransactionType(),estate.getEstateThumbNail(),
                estate.getAddress().getTown(),EstateType.of(house.getEstateType()),
                estate.getAddress().getBuildingName(),house.getCurrentFloors(),house.getRentableArea(),estate.getState(),house.getSellingFee());
        estateListResponseDtos.add(estateListResponseDto);
        Page<EstateListResponseDto> estateListResponsePageDtos = new PageImpl<EstateListResponseDto>(estateListResponseDtos, PageRequest.of(0, 3, Sort.unsorted()), 5);

        //given
        given(estateService.getAllEstate(any(),any()))
                .willReturn((estateListResponsePageDtos));

        //when
        mockMvc.perform(get("/api/estate/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[get] /api/estate/main")
    @Test
    @WithMockCustomUser
    void 메인페이지() throws Exception{
        List<EstateMainDto> mainDtos = new ArrayList<>();
        EstateMainDto estateMainDto = EstateMainDto.builder()
                .estateThumbNail("Thumbnail")
                .estateType(EstateType.APARTMENT)
                .id(1L)
                .sellingFee(123L)
                .thumbnail3D("3D_thumbnail")
                .town("동동")
                .transactionType(TransactionType.LEASE)
                .userBorough("강남구")
                .deposit(123L)
                .monthlyRent(2332L)
                .build();
        mainDtos.add(estateMainDto);

        //given
        given(estateService.getEstateCustomized(any()))
                .willReturn(mainDtos);

        //when
        mockMvc.perform(get("/api/estate/main"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[get] /api/estate/detail/{estateId}")
    @Test
    @WithMockCustomUser
    void 매물_상세_가져오기() throws Exception{
        EstateDetailDto estateDetailDto = EstateDetailDto.builder()
                .estate(estate)
                .assets(Arrays.asList(asset))
                .house(house.toEntity())
                .estateHit(new EstateHit(1L,1L))
                .broker(broker)
                .user(user)
                .build();

        //given
        given(estateService.getEstate(any(),any()))
                .willReturn(estateDetailDto);
        Long estateId = 1L;

        //when
        mockMvc.perform(get("/api/estate/detail/{estateId}",estateId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[post] /api/estate/detail/owner")
    @Test
    @WithMockCustomUser
    void 매물_등록하기_집주인() throws Exception{

        Long estateId = 1L;

        String content = objectMapper.writeValueAsString(address);
        given(estateService.saveFirst(any(),any(),any()))
                .willReturn(estateId);

        //when
        mockMvc.perform(post("/api/estate/detail/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("brokerId","1")
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("[post] /api/estate/detail/broker")
    @Test
    @WithMockCustomUser
    void 매물_등록하기_브로커() throws Exception{
        EstateSaveRequestDto estateSaveRequestDto = new EstateSaveRequestDto(1L,"MONTHLYRENT","도면","arcam",
                new HouseSaveRequestDto(house.getDeposit(),house.getMonthlyRent(),house.getSellingFee(),house.getCurrentFloors(),house.getTotalFloors(),house.isShortTermRent(), house.getMaintenanceFee(),
                        house.getItemsIncludedMaintenanceFee(),house.getNetRentableArea(),house.getRentableArea(),house.isParking(),house.getParkingFee(),house.getMoveInAvailableDate(),
                        house.getHeatType(),EstateType.of(house.getEstateType()),house.isElevator(),house.isDuplex(),house.getStructure().toString(),house.isVeranda()),
                Arrays.asList("photo1","photo2"),Arrays.asList("video1","video2"),
                Arrays.asList(new AssetSaveRequestDto(asset.getCategory().toString(), asset.getHousehold().toString(),asset.getProductName(),asset.getManufacturer(),asset.getAnchorId(),asset.getAssetPhoto())));
        EstateResponseDto estateResponseDto = EstateResponseDto.builder()
                .assets(Arrays.asList(asset))
                .estate(estate)
                .estateHit(new EstateHit(1L,1L))
                .house(house.toEntity())
                .build();

        String content = objectMapper.writeValueAsString(estateSaveRequestDto);
        given(estateService.saveEstate(any()))
                .willReturn(estateResponseDto);

        //when
        mockMvc.perform(post("/api/estate/detail/broker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("[put] /api/estate/detail/{estateId}")
    @Test
    @WithMockCustomUser
    void 매물_수정하기() throws Exception{

        Long estateId = 1L;

        EstateResponseDto estateResponseDto = EstateResponseDto.builder()
                .assets(Arrays.asList(asset))
                .estate(estate)
                .estateHit(new EstateHit(1L,1L))
                .house(house.toEntity())
                .build();

        String content = objectMapper.writeValueAsString(estateUpdateRequestDto);
        given(estateService.updateEstate(any(),any()))
                .willReturn(estateResponseDto);

        //when
        mockMvc.perform(put("/api/estate/detail/{estateId}",estateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[patch] /api/estate/detail/{estateId}/allowPost")
    @Test
    @WithMockCustomUser
    void 매물등록_컨펌하기() throws Exception{

        Long estateId = 1L;

        EstateResponseDto estateResponseDto = EstateResponseDto.builder()
                .assets(Arrays.asList(asset))
                .estate(estate)
                .estateHit(new EstateHit(1L,1L))
                .house(house.toEntity())
                .build();

        given(estateService.allowPost(any(),any()))
                .willReturn(estateResponseDto);

        //when
        mockMvc.perform(patch("/api/estate/detail/{estateId}/allowPost",estateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[patch] /api/estate/detail/{estateId}/dip")
    @Test
    @WithMockCustomUser
    void 매물_찜하기() throws Exception{
        Long estateId = 1L;
        PreferEstateResponseDto preferEstateResponseDto = PreferEstateResponseDto.builder()
                .estate(estate)
                .house(house.toEntity())
                .user(user)
                .build();

        given(preferEstateService.savePreferEstate(any(),any(),eq(Preference.DIP)))
                .willReturn(preferEstateResponseDto);

        //when
        mockMvc.perform(patch("/api/estate/detail/{estateId}/dip",estateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[patch] /api/estate/detail/{estateId}/inquiry")
    @Test
    @WithMockCustomUser
    void 매물_문의하기() throws Exception{
        Long estateId = 1L;
        PreferEstateResponseDto preferEstateResponseDto = PreferEstateResponseDto.builder()
                .estate(estate)
                .house(house.toEntity())
                .user(user)
                .build();

        given(preferEstateService.savePreferEstate(any(),any(),eq(Preference.INQUIRY)))
                .willReturn(preferEstateResponseDto);

        //when
        mockMvc.perform(patch("/api/estate/detail/{estateId}/inquiry",estateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[patch] /api/estate/detail/{estateId}/contract")
    @Test
    @WithMockCustomUser
    void 매물_계약하기() throws Exception{
        Long estateId = 1L;
        ContractStateResponseDto contractStateResponseDto = ContractStateResponseDto.builder()
                .contractState(ContractState.builder()
                        .state(State.BROKER_BEFORE)
                        .estate(estate)
                        .build())
                .build();

        given(estateService.startContract(any(),any()))
                .willReturn(contractStateResponseDto);

        //when
        mockMvc.perform(patch("/api/estate/detail/{estateId}/contract",estateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("[post] /api/estate/search/list")
    @Test
    @WithMockCustomUser
    void 매물_검색하기() throws Exception{
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        EstateListResponseDto estateListResponseDto = new EstateListResponseDto(estate.getId(),estate.getTransactionType(),estate.getEstateThumbNail(),
                estate.getAddress().getTown(),EstateType.of(house.getEstateType()),
                estate.getAddress().getBuildingName(),house.getCurrentFloors(),house.getRentableArea(),estate.getState(),house.getSellingFee());
        estateListResponseDtos.add(estateListResponseDto);

        AddressSearchDto addressSearchDto = new AddressSearchDto("강남구","동동");
        String content = objectMapper.writeValueAsString(addressSearchDto);
        given(estateService.searchEstate(any(),any(),any()))
                .willReturn(estateListResponseDtos);

        //when
        mockMvc.perform(post("/api/estate/search/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk());
    }
}


package com.example.Estate_Twin.user.controller;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.*;
import com.example.Estate_Twin.user.web.UserController;
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
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {UserController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

    User user;
    Asset asset;
    CheckList checkList;
    Address address;
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
                .category(Category.AIRCONDITIONER)
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("[get] /api/user/me")
    @Test
    @WithMockCustomUser
    void 마이페이지() throws Exception{
        UserInfoDto userInfoDto = new UserInfoDto(user);

        //given
        given(userService.getUser(any()))
                .willReturn(UserInfoDto.builder()
                        .user(user)
                        .build());

        //when
        mockMvc.perform(get("/api/user/me"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.phone").value(user.getPhone()));
    }

    @DisplayName("[get] /api/user/tenant/list")
    @Test
    @WithMockCustomUser
    void 세입자_매물_리스트() throws Exception{
        EstateModeDto estateModeDto = EstateModeDto.builder()
                .estateId(1L)
                .address(Address.builder()
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
                        .build())
                .estateType(EstateType.OFFICETELS)
                .state(State.BROKER_BEFORE)
                .build();

        //given
        given(userService.getTenantAssetList(any()))
                .willReturn(estateModeDto);

        //when
        mockMvc.perform(get("/api/user/tenant/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.estateId").value(1L));
    }

    @DisplayName("[get] /api/user/tenant/detail")
    @Test
    @WithMockCustomUser
    void 세입자_매물_리스트_상세() throws Exception{
        List<AssetResponseDto> assetResponseDtos = new ArrayList<>();
        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(checkList);
        AssetResponseDto assetResponseDto = AssetResponseDto.builder()
                .asset(asset)
                .checkLists(checkLists)
                .build();
        assetResponseDtos.add(assetResponseDto);

        //given
        given(userService.getTenantAsset(any(),any()))
                .willReturn(assetResponseDtos);
        //when
        mockMvc.perform(get("/api/user/tenant/detail")
                        .param("category", "BATHROOM"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category").value("BATHROOM"));
    }

    @DisplayName("[get] /api/user/owner/list")
    @Test
    @WithMockCustomUser
    void 집주인_매물_리스트() throws Exception{
        List<EstateModeDto> estateModeDtos = new ArrayList<>();
        EstateModeDto estateOwnerDto = EstateModeDto.builder()
                .estateId(1L)
                .address(address)
                .state(State.BROKER_BEFORE)
                .build();
        estateModeDtos.add(estateOwnerDto);

        //given
        given(userService.getOwnerAssetList(any()))
                .willReturn(estateModeDtos);

        //when
        mockMvc.perform(get("/api/user/owner/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].estateId").value(1L));
    }

    @DisplayName("[get] /api/user/owner/detail")
    @Test
    @WithMockCustomUser
    void 집주인_매물_리스트_상세() throws Exception{
        List<AssetResponseDto> assetResponseDtos = new ArrayList<>();
        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(checkList);
        AssetResponseDto assetResponseDto = AssetResponseDto.builder()
                .asset(asset)
                .checkLists(checkLists)
                .build();
        assetResponseDtos.add(assetResponseDto);

        //given
        given(userService.getOwnerAsset(any(),any()))
                .willReturn(assetResponseDtos);
        //when
        mockMvc.perform(get("/api/user/owner/detail")
                        .param("estateId", String.valueOf(1L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category").value("BATHROOM"));
    }

    @DisplayName("[post] /api/user/signup")
    @Test
    @WithMockCustomUser
    void 회원가입() throws Exception{
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .user(user)
                .build();

        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .birthday(user.getBirthday())
                .borough(user.getBorough())
                .estateType(user.getEstateType().toString())
                .phone(user.getPhone())
                .build();

        //given
        given(userService.signUp(any(), any()))
                .willReturn(userInfoDto);

        String content = objectMapper.writeValueAsString(userSignUpDto);
        //when
        mockMvc.perform(post("/api/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}

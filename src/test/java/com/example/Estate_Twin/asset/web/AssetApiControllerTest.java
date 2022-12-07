package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.service.impl.AssetServiceImpl;
import com.example.Estate_Twin.asset.web.dto.*;
import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.user.service.impl.OAuthService;
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
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {AssetApiController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class AssetApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    AssetServiceImpl assetService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    Asset asset;
    CheckList checkList;
    @BeforeEach
    void setting() {
        checkList = CheckList.builder()
                .checkListPhoto("checklist_photo")
                .repairType(RepairType.REPAIR)
                .checkListContent("content")
                .flawPart("하자부위")
                .repairDate(LocalDate.of(2022,06,12))
                .build();
        asset = Asset.builder()
                .assetPhoto("asset_photo")
                .anchorId("anchor_id")
                .manufacturer("LG 가전")
                .productName("냉장고")
                .category(Category.BATHROOM)
                .household(Household.AIRCONDITIONER)
                .build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @DisplayName("[get] /api/asset/{assetId}")
    @Test
    @WithMockCustomUser
    void 에셋_가져오기() throws Exception{
        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(checkList);
        AssetResponseDto assetResponseDto = AssetResponseDto.builder()
                .checkLists(checkLists)
                .asset(asset)
                .build();
        given(assetService.getAsset(any()))
                .willReturn((assetResponseDto));

        Long assetId = 1L;
        //when
        mockMvc.perform(get("/api/asset/{assetId}",assetId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[post] /api/asset/estate/{estateId}")
    @Test
    @WithMockCustomUser
    void 에셋_등록하기() throws Exception{
        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(checkList);

        AssetSaveRequestDto assetSaveRequestDto = new AssetSaveRequestDto("HOMEAPPLIANCES","AIRCONDITIONER","제품이름","제조사 삼성","1","에셋사진");
        Asset asset1 = assetSaveRequestDto.toEntity();
        AssetResponseDto assetResponseDto = AssetResponseDto.builder()
                .checkLists(checkLists)
                .asset(asset1)
                .build();

        String content = objectMapper.writeValueAsString(assetSaveRequestDto);
        given(assetService.saveAsset(any(),any()))
                .willReturn((assetResponseDto));

        Long estateId = 1L;
        //when
        mockMvc.perform(post("/api/asset/estate/{estateId}",estateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("[put] /api/asset/{assetId}")
    @Test
    @WithMockCustomUser
    void 에셋_수정하기() throws Exception{
        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(checkList);

        AssetUpdateRequestDto assetUpdateRequestDto = new AssetUpdateRequestDto("HOMEAPPLIANCES","AIRCONDITIONER","제품이름","제조사 삼성","1","에셋사진");
        asset.update(assetUpdateRequestDto);
        AssetResponseDto assetResponseDto = AssetResponseDto.builder()
                .checkLists(checkLists)
                .asset(asset)
                .build();

        String content = objectMapper.writeValueAsString(assetUpdateRequestDto);
        given(assetService.updateAsset(any(),any()))
                .willReturn((assetResponseDto));

        Long assetId = 1L;
        //when
        mockMvc.perform(put("/api/asset/{assetId}",assetId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.service.impl.CheckListServiceImpl;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.user.service.impl.*;
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
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CheckListApiController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class CheckListApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    CheckListServiceImpl checkListService;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    OAuthService oAuthService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("[get] /api/checklist/asset/{assetId}")
    @Test
    @WithMockCustomUser
    void 에셋에_따른_체크리스트_가져오기() throws Exception{
        List<CheckListResponseDto> checkListResponseDtos = new ArrayList<>();
        CheckListResponseDto checkListResponseDto = new CheckListResponseDto(checkList);
        checkListResponseDtos.add(checkListResponseDto);

        given(checkListService.getAllCheckListByAssetId(any()))
                .willReturn((checkListResponseDtos));
        Long assetId = 1L;
        //when
        mockMvc.perform(get("/api/checklist/asset/{assetId}",assetId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[get] /api/checklist/{checklistId}")
    @Test
    @WithMockCustomUser
    void 체크리스트_가져오기() throws Exception{
        CheckListResponseDto checkListResponseDto = new CheckListResponseDto(checkList);

        given(checkListService.getCheckList(any()))
                .willReturn((checkListResponseDto));
        Long checklistId = 1L;
        //when
        mockMvc.perform(get("/api/checklist/{checklistId}",checklistId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[post] /api/checklist/estate/{estateId}/asset/{assetId}")
    @Test
    @WithMockCustomUser
    void 체크리스트_등록하기() throws Exception{
        Long estateId = 1L;
        Long assetId = 1L;

        CheckListSaveRequestDto checkListSaveRequestDto = new CheckListSaveRequestDto("flawPart","content",LocalDate.now(),"PURCHASE","checklist_photo");
        CheckList checkList1 = checkListSaveRequestDto.toEntity();
        CheckListResponseDto checkListResponseDto = new CheckListResponseDto(checkList1);
        //given
        given(checkListService.saveCheckList(any(), any(),any(),any()))
                .willReturn(checkListResponseDto);

        String content = objectMapper.writeValueAsString(checkListSaveRequestDto);
        //when
        mockMvc.perform(post("/api/checklist/estate/{estateId}/asset/{assetId}",estateId,assetId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flawPart").value("flawPart"));
    }

    @DisplayName("[put] /api/checklist/{checklistId}")
    @Test
    @WithMockCustomUser
    void 체크리스트_수정하기() throws Exception{
        Long checklistId = 1L;

        CheckListUpdateRequestDto checkListUpdateRequestDto = new CheckListUpdateRequestDto("flawPart","content","PURCHASE","checklist_photo",LocalDate.now());
        checkList.update(checkListUpdateRequestDto);
        CheckListResponseDto checkListResponseDto = new CheckListResponseDto(checkList);
        //given
        given(checkListService.updateCheckList(any(), any()))
                .willReturn(checkListResponseDto);

        String content = objectMapper.writeValueAsString(checkListUpdateRequestDto);
        //when
        mockMvc.perform(put("/api/checklist/{checklistId}",checklistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flawPart").value("flawPart"));
    }

    @DisplayName("[patch] /api/checklist/{checklistId}/estate/{estateId}/confirm")
    @Test
    @WithMockCustomUser
    void 체크리스트_컨펌하기() throws Exception{
        Long checklistId = 1L;
        Long estateId = 1L;

        CheckListResponseDto checkListResponseDto = new CheckListResponseDto(checkList);

        //given
        given(checkListService.confirmCheckList(any(), any(), any()))
                .willReturn(checkListResponseDto);

        //when
        mockMvc.perform(patch("/api/checklist/{checklistId}/estate/{estateId}/confirm",checklistId, estateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
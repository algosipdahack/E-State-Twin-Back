package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.service.impl.CheckListServiceImpl;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.user.service.impl.OAuthService;
import com.example.Estate_Twin.user.service.impl.UserServiceImpl;
import com.example.Estate_Twin.user.web.UserController;
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
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {UserController.class})
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

    CheckList checkList;
    @BeforeEach
    void setting() {
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
    }

    @DisplayName("[get] /api/checklist/asset/{assetId}")
    @Test
    @WithMockCustomUser
    void 체크리스트_가져오기() throws Exception{
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



}
package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = EstateApiController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstateApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Test
    public void uploadFile() throws Exception {
        MockMultipartFile multipartFile1 = new MockMultipartFile("file","test.png","image/png","test file".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile multipartFile2 = new MockMultipartFile("file","test2.png","image/png","test file2".getBytes(StandardCharsets.UTF_8));
        MediaSaveRequestDto mediaDto = new MediaSaveRequestDto("sdf","sfd");
        String mediaDtoJson = mapper.writeValueAsString(mediaDto);
        MockMultipartFile media = new MockMultipartFile("media","media","application/json",mediaDtoJson.getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart("/api/estate/")
                        .file(multipartFile1).part(new MockPart("id","foo1".getBytes(StandardCharsets.UTF_8)))
                        .file(multipartFile2).part(new MockPart("id","foo2".getBytes(StandardCharsets.UTF_8)))
                        .file(media)
                )
                .andDo(print());
    }
}
    /*
    @MockBean
    EstateServiceImpl estateService;
    @MockBean
    HouseServiceImpl houseService;
    @MockBean
    AddressServiceImpl addressService;

    EstateSaveRequestDto estateSaveRequestDto;
    AddressSaveRequestDto addressSaveRequestDto;
    AddressUpdateRequestDto addressUpdateRequestDto;
    ContractStateUpdateRequestDto contractStateUpdateRequestDto;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void 설정() {
        addressSaveRequestDto = new AddressSaveRequestDto()
                .builder()
                .city("경기도")
                .borough("고양시")
                .town("화정동")
                .complexName("6단지")
                .block("123동")
                .unit("3455호")
                .roadName("화정로 27")
                .mainBuildingNumber(234)
                .subBuildingNumber(556)
                .buildingName("00빌딩")
                .build();
        addressUpdateRequestDto = new AddressUpdateRequestDto(addressSaveRequestDto.getCity(), addressSaveRequestDto.getBorough(), addressSaveRequestDto.getTown(), addressSaveRequestDto.getComplexName(),
                addressSaveRequestDto.getBlock(), addressSaveRequestDto.getUnit(), addressSaveRequestDto.getRoadName(), addressSaveRequestDto.getMainBuildingNumber(),
                addressSaveRequestDto.getSubBuildingNumber(), addressSaveRequestDto.getBuildingName());
        contractStateUpdateRequestDto = new ContractStateUpdateRequestDto()
                .builder()
                .state(State.AFTER)
                .build();
        estateSaveRequestDto = new EstateSaveRequestDto()
                .builder()
                .transactionType("MONTHLYRENT")
                .estateThumbNail("썸네일 SRC")
                .content("내용내용")
                .model("모델src")
                .address(addressSaveRequestDto)
                .contractState(contractStateUpdateRequestDto)
                .build();
    }

    // post(http://localhost:8080/api/estate/detail/{houseId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("Estate 데이터 생성 테스트")
    public void 매물만들기() throws Exception {
        //given
        String content = objectMapper.writeValueAsString(estateSaveRequestDto);
        Long houseId = 2L;
        MockMultipartFile file = new MockMultipartFile("image","test.png","image/png",new FileInputStream("업로드 할 실제 파일 path 입력"));
        this.mockMvc.perform(multipart("/images")
                .file(file).part(new MockPart("estate","foo".getBytes(StandardCharsets.UTF_8))))
                        .andDo(print())
                                .andExpect(status().isOk());
        //when
        mockMvc.perform(
                        post("/api/estate/detail/{houseId}",houseId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andDo(print());

    }

    // get(http://localhost:8080/api/estate/detail/{estateId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("Estate 데이터 반환 테스트")
    public void 매물가져오기() throws Exception {
        Long estateId = 2L;
        mockMvc.perform(
                        get("/api/estate/detail/{estateId}",estateId)
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // put(http://localhost:8080/api/estate/detail/{estateId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("Estate 데이터 수정 테스트")
    public void 매물수정하기() throws Exception {
        Long estateId = 2L;
        EstateUpdateRequestDto estateUpdateRequestDto = new EstateUpdateRequestDto(estateSaveRequestDto.getTransactionType().toString(),
                estateSaveRequestDto.getEstateThumbNail(), estateSaveRequestDto.getContent(), estateSaveRequestDto.getModel(),
        estateSaveRequestDto.getEstateThumbNail(), addressUpdateRequestDto);
        String content = objectMapper.writeValueAsString(estateUpdateRequestDto);
        mockMvc.perform(
                        put("/api/estate/detail/{estateId}",estateId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }
}*/
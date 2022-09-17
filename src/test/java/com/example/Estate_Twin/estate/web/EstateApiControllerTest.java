package com.example.Estate_Twin.estate.web;

import com.amazonaws.util.IOUtils;
import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class EstateApiControllerTest {
    @Autowired
    private EstateApiController estateApiController;
    @Test
    public void uploadFile() throws Exception {
        AddressSaveRequestDto addressSaveRequestDto = new AddressSaveRequestDto()
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

        HouseSaveRequestDto houseSaveRequestDto =  HouseSaveRequestDto.builder()
                .bathCount(1L)
                .usageAvailableDate(LocalDateTime.now())
                .roomCount(1L)
                .household(1L)
                .estateType(EstateType.APARTMENT)
                .heatType("heattype")
                .size(1L)
                .moveInAvailableDate(LocalDateTime.now())
                .parkingFee(1L)
                .parking(true)
                .rentableArea(1L)
                .netRentableArea(1L)
                .itemsIncludedMaintenanceFee("fee")
                .maintenanceFee(1L)
                .shortTermRent(true)
                .totalFloors(1L)
                .currentFloors(1L)
                .sellingFee(1L)
                .monthlyRent(1L)
                .deposit(1L)
                .monthlyRent(1L)
                .build();

        EstateSaveRequestDto estateSaveRequestDto = new EstateSaveRequestDto();

        //DTO 생성
        List<MediaSaveMultipartRequestDto> mediaSaveMultipartRequestDtos = new ArrayList<>();
        List<AssetSaveRequestDto> assetSaveRequestDtos = new ArrayList<>();
        // Images
        String[] ImageFiles = {"/Users/mincho/Downloads/photo.png", "/Users/mincho/Downloads/image.png"};

        // Image 생성
        for (int i = 0; i < ImageFiles.length; i++) {
            File imageFile = new File(ImageFiles[i]);
            FileInputStream inputStream = new FileInputStream(imageFile);
            MultipartFile multipartFile = new MockMultipartFile(imageFile.getName(), imageFile.getName(), "image/jpg", IOUtils.toByteArray(inputStream));

            MediaSaveMultipartRequestDto mediaSaveMultipartRequestDto = new MediaSaveMultipartRequestDto();
            mediaSaveMultipartRequestDto.setImageFile(multipartFile);

            mediaSaveMultipartRequestDtos.add(mediaSaveMultipartRequestDto);
        }

        for (int i = 0; i < ImageFiles.length; i++) {
            AssetSaveRequestDto assetSaveRequestDto = new AssetSaveRequestDto().builder()
                    .assetName("변기")
                    .productName("대림바스")
                    .category(Category.BATHROOM)
                    .assetPhotos(mediaSaveMultipartRequestDtos)
                    .build();
            assetSaveRequestDtos.add(assetSaveRequestDto);
        }

        estateSaveRequestDto.builder()
                .model("src")
                .address(addressSaveRequestDto)
                .content("1")
                .estateThumbNail("src")
                .transactionType("MONTHLYRENT")
                .estatePhotos(mediaSaveMultipartRequestDtos)
                .house(houseSaveRequestDto)
                .assets(assetSaveRequestDtos)
                .build();

        ResponseEntity<EstateResponseDto> responseEntity = estateApiController.saveEstate(estateSaveRequestDto);
        log.info(responseEntity.getBody().toString());
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
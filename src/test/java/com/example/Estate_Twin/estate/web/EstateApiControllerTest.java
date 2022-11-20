package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.config.WithMockCustomUser;
import com.example.Estate_Twin.user.service.impl.OAuthService;
import com.example.Estate_Twin.user.service.impl.UserServiceImpl;
import com.example.Estate_Twin.user.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {UserController.class})
@MockBean(JpaMetamodelMappingContext.class)
public class EstateApiControllerTest {
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

    /*AddressSaveRequestDto addressSaveRequestDto;
    HouseSaveRequestDto houseSaveRequestDto;
    @BeforeEach
    public void setUp() throws Exception {
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

        houseSaveRequestDto =  HouseSaveRequestDto.builder()
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
    }
    @Test
    public void uploadFile() throws Exception {

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
                    .option("CLOSET")
                    .productName("대림바스")
                    .category("BATHROOM")
                    .assetPhotos(mediaSaveMultipartRequestDtos)
                    .manufacturer("LG")
                    .build();
            assetSaveRequestDtos.add(assetSaveRequestDto);
        }

        EstateSaveRequestDto estateSaveRequestDto = new EstateSaveRequestDto().builder()
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
    }*/
    @Test
    @WithMockCustomUser
    @DisplayName("메인페이지를 호출한다.")
    public void 메인페이지(){

    }
 /*
    @Test
    public void getEstateOne() throws Exception {
        ResponseEntity<EstateResponseDto> responseEntity = estateApiController.getEstate(1L);
        log.info(responseEntity.getBody().getAssets().toString());
    }
    @Test
    public void getEstateMain() throws Exception {
        ResponseEntity<List<EstateMainDto>> responseEntity = estateApiController.getList("강남구");
        log.info(responseEntity.getBody().toString());
    }

*/
}


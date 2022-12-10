package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class EstateApiControllerTotalTest {
    @Autowired
    EstateService estateService;
    EstateSaveRequestDto dto;
    Address address;
    HouseUpdateRequestDto house;
    Asset asset;

    @BeforeEach
    public void setUp() {
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
                .category(Category.AIRCONDITIONER)
                .build();
        house =  new HouseUpdateRequestDto(1L,1L,1L,1L,1L,false,1L,"fee",1L,1L,false,1L,LocalDate.now(),"heattype","OFFICETELS",false,false,"LOFT",true);
        dto = new EstateSaveRequestDto(2L,"MONTHLYRENT","도면","arcam",
                new HouseSaveRequestDto(house.getDeposit(),house.getMonthlyRent(),house.getSellingFee(),house.getCurrentFloors(),house.getTotalFloors(),house.isShortTermRent(), house.getMaintenanceFee(),
                        house.getItemsIncludedMaintenanceFee(),house.getNetRentableArea(),house.getRentableArea(),house.isParking(),house.getParkingFee(),house.getMoveInAvailableDate(),
                        house.getHeatType(),EstateType.of(house.getEstateType()),house.isElevator(),house.isDuplex(),house.getStructure().toString(),house.isVeranda()),
                Arrays.asList("photo1","photo2"),Arrays.asList("video1","video2"),
                Arrays.asList(new AssetSaveRequestDto(asset.getCategory().toString(), asset.getProductName(),asset.getManufacturer(),asset.getAnchorId(),asset.getAssetPhoto())));

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 매물등록_집주인() throws Exception {
        estateService.saveFirst(address, 1L, 1L);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 매물등록_브로커() throws Exception {
        estateService.saveEstate(dto);
    }
}

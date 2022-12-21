package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class EstateApiServiceTotalTest {
    @Autowired
    EstateService estateService;
    @Autowired
    EstateDAO estateDAO;
    @Autowired
    EstateHitRepository estateHitRepository;
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

    @Test
    @Transactional
    @DisplayName("get Estate 요청을 n 번 호출 하는경우 조회수는 n 이 되어야함.")
    void getEstate() throws InterruptedException {
        //given
        ExecutorService service = Executors.newFixedThreadPool(10);
        //when
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    Estate estate = estateDAO.getEstate(1L);
                    System.out.println(estate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1000);
        //then
        EstateHit estateHit = estateHitRepository.findEstateHitByEstate_Id(1L).orElseThrow(() ->new IllegalArgumentException("해당 id를 가진 estate가 없습니다."+1L));
        assertThat(estateHit.getWeeklyHit()).isEqualTo(10);
    }

}

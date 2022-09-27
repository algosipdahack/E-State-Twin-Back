package com.example.Estate_Twin.estate.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
/*
public class EstateServiceTest {
    private EstateRepository estateRepository = Mockito.mock(EstateRepository.class);
    private EstateHitRepository estateHitRepository = Mockito.mock(EstateHitRepository.class);
    private ContractStateRepository contractStateRepository = Mockito.mock(ContractStateRepository.class);
    private EstateDAOImpl estateDAO;
    private Estate estate;
    @BeforeEach
    void setUpTest() {
        estateDAO = new EstateDAOImpl(estateRepository,estateHitRepository,contractStateRepository);
        Address address = new Address().builder()
                .city("서울특별시")
                .borough("강남구")
                .town("개포동")
                .complexName("1단지")
                .block("1동")
                .unit("1호")
                .roadName("1로")
                .mainBuildingNumber(1)
                .subBuildingNumber(1)
                .buildingName("1건물")
                .build();
        House house = new House().builder()
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
        house.setId(1L);
        Asset asset1 = new Asset().builder()
                .assetName("의자")
                .category(Category.FURNITURE)
                .productName("체어클럽")
                .manufacturer("LG")
                .build();
        asset1.setId(1L);
        Asset asset2 = new Asset().builder()
                .assetName("식탁")
                .category(Category.FURNITURE)
                .productName("토미")
                .manufacturer("LG")
                .build();
        asset2.setId;
        Asset asset3 = new Asset().builder()
                .assetName("벽지")
                .category(Category.INTERIOR)
                .productName("코스모스")
                .manufacturer("LG")
                .build();
        estate = new Estate().builder()
                .estateThumbNail("src")
                .content("content")
                .model("model")
                .city("경기도")
                .borough("고양시")
                .town("화정동")
                .thumbnail3D("3D")
                .transactionType(TransactionType.LEASE)
                .address(address)
                .build();
        estate.setId(1L);

    }
    @Test
    void getHouseTest() {
        Mockito.when(estateRepository.findById(1L))
                .thenReturn(Optional.of(estate));

        EstateResponseDto estateResponseDto = estateDAO.getHouse(123L);
        Assertions.assertEquals(houseResponseDto.getBathCount(),givenHouse.getBathCount());
        System.out.println(houseResponseDto.getHeatType());
        verify(houseRepository).findById(123L); // 해당 함수가 호출되었는지를 검증
    }

    @Test
    void saveHouseTest() {
        Mockito.when(houseRepository.save(any(House.class)))
                .then(returnsFirstArg());
        HouseResponseDto houseResponseDto = houseService.saveHouse(new HouseSaveRequestDto(givenHouse.getDeposit(),givenHouse.getMonthlyRent(),givenHouse.getSellingFee(),
                givenHouse.getCurrentFloors(),givenHouse.getTotalFloors(),givenHouse.isShortTermRent(),givenHouse.getMaintenanceFee(),
                givenHouse.getItemsIncludedMaintenanceFee(),givenHouse.getNetRentableArea(),givenHouse.getRentableArea(),
                givenHouse.isParking(),givenHouse.getParkingFee(),givenHouse.getMoveInAvailableDate(),givenHouse.getUsageAvailableDate(),
                givenHouse.getSize(),givenHouse.getHeatType(),givenHouse.getEstateType(),givenHouse.getHousehold(),givenHouse.getRoomCount(),
                givenHouse.getBathCount()));
        Assertions.assertEquals(houseResponseDto.getBathCount(),givenHouse.getBathCount());
        verify(houseRepository).save(any());
    }
}
*/

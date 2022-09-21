package com.example.Estate_Twin.asset.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
//@AutoConfigureTestDatabase()
/*public class AssetServiceTest {
    private HouseRepository houseRepository = Mockito.mock(HouseRepository.class);
    private AssetRepository assetRepository = Mockito.mock(AssetRepository.class);
    private AssetDAOImpl assetDAO;
    private HouseDAOImpl houseDAO;
    private AssetServiceImpl assetService;
    private Asset asset;
    private House house;
    @BeforeEach
    public void setUpTest() {
        assetDAO =  new AssetDAOImpl(assetRepository);
        houseDAO = new HouseDAOImpl(houseRepository);
        assetService = new AssetServiceImpl(assetDAO,houseDAO);

        house = new House().builder()
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
        asset = new Asset().builder()
                .assetName("의자")
                .category(Category.FURNITURE)
                .productName("체어클럽")
                .manufacturer("LG")
                .build();
        asset.setHouse(house);
        asset.setId(1L);
    }

    @Test
    void getAssetTest() {
        Mockito.when(assetRepository.findById(1L))
                .thenReturn(Optional.of(asset));
        AssetResponseDto assetResponseDto = assetService.getAsset(asset.getId());
        Assertions.assertEquals(assetResponseDto.getAssetName(),asset.getAssetName());
        verify(assetRepository).findById(1L); // 해당 함수가 호출되었는지를 검증
    }

    @Test
    void saveAssetTest() {
        Mockito.when(houseRepository.findById(1L))
                .thenReturn(Optional.of(house));
        Mockito.when(assetRepository.save(asset))
                .thenReturn(asset);

        assetDAO.saveAsset(asset,house);

        verify(assetRepository).save(any());
    }


*/
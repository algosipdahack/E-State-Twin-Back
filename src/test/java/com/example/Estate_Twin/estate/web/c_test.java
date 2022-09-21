package com.example.Estate_Twin.estate.web;

/*
public class c_test {
    @Getter
    @Setter
    @NoArgsConstructor
    public class BranchImageCreateRequestDto {

        @NotNull
        private String fileName;

        private String fileUrl;

        private int orders;

        @NotNull
        private MultipartFile imageFile;

        /**
         * Dto to Entity
         * @PropertySource가 먹히지않음
         *
        public BranchImage toEntity() {
            return BranchImage.builder()
                    .fileName(this.fileName)
                    .fileUrl(this.fileUrl)
                    .orders(this.orders)
                    .build();
        }
    }


    public void create() throws IOException {

        // 창고등록자 아이디
        Long userId = 3L;

        // Branch 생성 정보
        String storageType = "s";
        String branchName = "병아리색지하철형스토리지";
        String zipcode = "05699";
        String roadAddress = "서울 송파구 송파대로 지하 257";
        String streetAddress = "서울 송파구 가락동 184-23";
        String detailAddress = "1";
        String branchStartTime = "05:00";
        String branchEndTime = "20:00";
        String branchPhone = "01067892203";
//        String branchWeb = "";
        String branchDescription = "도심내 주요 역사에 나만의 창고를 이용해 보세요.\n" +
                "\n" +
                "지하철 출, 퇴근길 간편하게 보관하고 찾을 수 있는 메트로 셀프 스토리지 서비스 입니다.\n" +
                "\n" +
                "지하철 운행 종류 후에는 물품을 찾거나 보관할 수 없습니다. ";
        String refundPolicy = "- 전액을 환불받으려면, 게스트가 숙소의 현지 체크인 시간 기준 5일 전까지 취소해야 합니다.\n" +
                "- 게스트가 체크인 시간 24시간 전 또는 그 이후에 예약을 취소하는 경우 환불이 불가합니다.\n" +
                "- 창고의 물품 및 시설에 대한 손상이 생길 경우 사용자가 전액 배상하게 됩니다.\n" +
                "- 예정 된 창고 이용시간보다 더 사용하실 경우 미리 호스트에게 연락을 하여 추가 예약을 해야합니다.\n" +
                "- 예정 된 창고 이용시간을 2시간 이상 넘길 경우 창고주는 임의로 게스트의 물건을 창고 밖으로 이동할 수 있습니다.";
        int minUsePeriod = 30;
        int depositStatus = 1;
        String deposit = "캐비넷형은 10000원이며 룸형은 20000원입니다. 사용이 끝나면 다시 돌려드립니다.";
        int pickupStatus = 1;
        String pickup = "원하시는곳에서 수거하여 가져가드립니다.";
        int deliveryStatus = 1;
        String delivery = "예약이 끝나는날 짐을 받고싶으신 곳을 정할 수 있습니다.";

        BranchCreateRequestDto branchCreateRequestDto = new BranchCreateRequestDto();
        branchCreateRequestDto.setStorageType(storageType);
        branchCreateRequestDto.setBranchName(branchName);
        branchCreateRequestDto.setZipcode(zipcode);
        branchCreateRequestDto.setRoadAddress(roadAddress);
        branchCreateRequestDto.setStreetAddress(streetAddress);
        branchCreateRequestDto.setDetailAddress(detailAddress);
        branchCreateRequestDto.setBranchStartTime(branchStartTime);
        branchCreateRequestDto.setBranchEndTime(branchEndTime);
        branchCreateRequestDto.setBranchPhone(branchPhone);
//        branchCreateRequestDto.setBranchWeb(branchWeb);
        branchCreateRequestDto.setBranchDescription(branchDescription);
        branchCreateRequestDto.setRefundPolicy(refundPolicy);
        branchCreateRequestDto.setMinUsePeriod(minUsePeriod);
        branchCreateRequestDto.setDepositStatus(depositStatus);
        branchCreateRequestDto.setDeposit(deposit);
        branchCreateRequestDto.setPickupStatus(pickupStatus);
        branchCreateRequestDto.setPickup(pickup);
        branchCreateRequestDto.setDeliveryStatus(deliveryStatus);
        branchCreateRequestDto.setDelivery(delivery);

        // Amenities 생성 정보
        int parking = 1;
        int sellBox = 1;
        int elevator = 1;
        int supportItem = 1;
        int valuables = 0;
        int animal = 0;
        int volatility = 0;
        int food = 0;
        int cctv = 1;
        int insurance = 1;
        int hvac = 1;
        int doorLock = 1;
        int twentyFour = 1;

        AmenitiesBasicDto amenitiesBasicDto = new AmenitiesBasicDto();

        amenitiesBasicDto.setParking(parking);
        amenitiesBasicDto.setSellBox(sellBox);
        amenitiesBasicDto.setElevator(elevator);
        amenitiesBasicDto.setSupportItem(supportItem);
        amenitiesBasicDto.setCctv(cctv);
        amenitiesBasicDto.setInsurance(insurance);
        amenitiesBasicDto.setHvac(hvac);
        amenitiesBasicDto.setDoorLock(doorLock);
        amenitiesBasicDto.setTwentyFour(twentyFour);

        // amenities set
        branchCreateRequestDto.setAmenities(amenitiesBasicDto);

        List<BranchImageCreateRequestDto> branchImageCreateRequestDtos = new ArrayList<>();

        // BranchImages
        String[] branchImageFiles = {/Users/mincho/Downloads/photo.png", "/Users/mincho/Downloads/image.png"};

        // BranchImage 생성
        for (int i = 0; i < branchImageFiles.length; i++) {
            File imageFile = new File(branchImageFiles[i]);
            FileInputStream inputStream = new FileInputStream(imageFile);
            MultipartFile multipartFile = new MockMultipartFile(imageFile.getName(), imageFile.getName(), "image/jpg", IOUtils.toByteArray(inputStream));

            BranchImageCreateRequestDto branchImageCreateRequestDto = new BranchImageCreateRequestDto();
            branchImageCreateRequestDto.setFileName(imageFile.getName());
            branchImageCreateRequestDto.setImageFile(multipartFile);

            branchImageCreateRequestDtos.add(branchImageCreateRequestDto);
        }

        // branchImages set
        branchCreateRequestDto.setBranchImages(branchImageCreateRequestDtos);

        // Unit 정보
        // Unit 2개
        String[] unitNames = {"캐비넷형", "룸형"};
        String[] unitDescriptions = {"캐비넷형 셀프스토리지 입니다.", "룸형 셀프스토리지 입니다."};
        int[] widths = {100, 150};
        int[] heights = {100, 100};
        int[] depths = {210, 210};
        BigDecimal[] priceValues = {new BigDecimal(63200), new BigDecimal(104800)};
        String[] pricePeriods = {"월", "월"};
        int[] counts = {5, 3};

        List<UnitCreateRequestDto> unitCreateRequestDtos = new ArrayList<>();

        // UnitImage 정보 - 로컬에서 불러오기
        List<String[]> unitFiles = new ArrayList<>();

        String[] unit1FileUrls = {"/Users/dong/Downloads/8_3.jpg", "/Users/dong/Downloads/8_4.jpg"};
        String[] unit2FileUrls = {"/Users/dong/Downloads/8_5.jpg"};

        unitFiles.add(unit1FileUrls);
        unitFiles.add(unit2FileUrls);

        // unit length
        for (int i = 0; i < unitNames.length; i++) {
            UnitCreateRequestDto unitCreateRequestDto = new UnitCreateRequestDto();
            unitCreateRequestDto.setUnitName(unitNames[i]);
            unitCreateRequestDto.setUnitDescription(unitDescriptions[i]);
            unitCreateRequestDto.setWidth(widths[i]);
            unitCreateRequestDto.setHeight(heights[i]);
            unitCreateRequestDto.setDepth(depths[i]);
            unitCreateRequestDto.setPriceValue(priceValues[i]);
            unitCreateRequestDto.setPricePeriod(pricePeriods[i]);
            unitCreateRequestDto.setCount(counts[i]);

            List<UnitImageCreateRequestDto> unitImageCreateRequestDtos = new ArrayList<>();

            // unit1 이미지
            for (int j = 0; j < unitFiles.get(i).length; j++) {
                File imageFile = new File(unitFiles.get(i)[j]);
                FileInputStream inputStream = new FileInputStream(imageFile);
                MultipartFile multipartFile = new MockMultipartFile(imageFile.getName(), imageFile.getName(), "image/jpg", IOUtils.toByteArray(inputStream));

                UnitImageCreateRequestDto unitImageCreateRequestDto = new UnitImageCreateRequestDto();

                unitImageCreateRequestDto.setFileName(imageFile.getName());
                unitImageCreateRequestDto.setImageFile(multipartFile);

                unitImageCreateRequestDtos.add(unitImageCreateRequestDto);
            }

            // unitImage 리스트 추가
            unitCreateRequestDto.setUnitImages(unitImageCreateRequestDtos);

            // unit 리스트 추
            unitCreateRequestDtos.add(unitCreateRequestDto);
        }

        // units set
        branchCreateRequestDto.setUnits(unitCreateRequestDtos);

        ResponseEntity<BranchBasicDto> responseEntity = branchApiController.createBranch(userId, branchCreateRequestDto);

        logger.info(responseEntity.getBody().toString());
    }
}
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String city;
    private String borough;
    private String town;
    private String model;
    private List<MediaSaveMultipartRequestDto> estatePhotos;
    private AddressSaveRequestDto address;
    private HouseSaveRequestDto house;
    private List<AssetSaveRequestDto> assetSaveRequestDtos;
        private Category category;
        private String assetName;
        private String productName;
        private String manufacturer;
        private List<MediaSaveMultipartRequestDto> assetPhotos;
    assetName="변기" productName="대림바스" category="BATHROOM"
    assetName="벽지" productName="코스모스" category="INTERIOR"
    estateThumbNail="src" content="1"
    model="src" city="서울특별시" borough="강남구" town="개포동"
    thumbnail3D="src" isPosted="false" ownerConfirmYN="false" brokerConfirmYN="false" transactionType="MONTHLYRENT"
    state="BEFORE" house_id="1" broker_id="1" owner_id="2" tanent_id="3" +
*/
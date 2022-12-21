package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.web.dto.BrokerSignUpDto;
import com.example.Estate_Twin.user.web.dto.BrokerSummaryDto;
import com.example.Estate_Twin.user.web.dto.UserInfoDto;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class UserServiceTotalTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BrokerService brokerService;
    User user1;
    User user2;
    Broker broker;
    Address address;
    UserSignUpDto userSignUpDto;
    BrokerSignUpDto brokerSignUpDto;
    @BeforeEach
    public void setUp() {
        String date = "2022-10-22";
        user1 = User.builder()
                .borough("강남구")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("조소연")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        user2 = User.builder()
                .borough("강남구")
                .estateType(EstateType.APARTMENT)
                .transactionType(TransactionType.MONTHLYRENT)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("이종혁")
                .role(Role.USER)
                .authProvider(AuthProvider.GOOGLE)
                .email("redred5253@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        userSignUpDto = new UserSignUpDto(user1.getBirthday(),user1.getPhone(),user1.getTransactionType().toString(),user1.getEstateType().toString(),user1.getBorough());
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
        broker = Broker.builder()
                .content("내용")
                .brokerPhoto("broker_photo")
                .brokerageRegistrationLicense("registration_license")
                .businessLicense("business_license")
                .address(address)
                .brokerageRegistrationNumber("brokerage_number")
                .agentName("000공인중개사")
                .businessName("000공인중개소")
                .businessRegistrationNumber("business_number")
                .build();
        brokerSignUpDto = new BrokerSignUpDto(broker.getBusinessName(),broker.getAgentName(),broker.getBrokerageRegistrationNumber(),
                broker.getBusinessRegistrationNumber(),broker.getBusinessLicense(),broker.getBrokerageRegistrationLicense(),broker.getBrokerPhoto(),
                broker.getContent(),broker.getAddress());
    }
    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        UserInfoDto userInfoDto = userService.signUp(user1,userSignUpDto);
    }

    @Test
    @Rollback(value = false)
    public void 브로커_회원가입() throws Exception {
        BrokerSummaryDto userInfoDto = brokerService.signUpBroker(user2,brokerSignUpDto);
    }
}

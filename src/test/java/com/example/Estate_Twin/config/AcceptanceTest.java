package com.example.Estate_Twin.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

//테스트를 실행하기 전에 새로운 app 컨텍스트를 생성해서 사용가능
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
    @LocalServerPort // application.yml의 port값으로 SpringBootTest를 실행
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
}

package com.example.Estate_Twin.web;

import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstateApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EstateRepository estateRepository;
    @After
    public void cleanup() throws Exception {
        estateRepository.deleteAll();
    }
    /*@Test
    public void post_estate() throws Exception {

        EstateSaveRequestDto estateSaveRequestDto = EstateSaveRequestDto.builder()
                .ad_distinct("dd")
                .address("sd")
                .city("gda")
                .content("asdf")
                .estateThumbNail("Adf")
                .transactionType("monthlyrent")
                .build();
    }

}*/
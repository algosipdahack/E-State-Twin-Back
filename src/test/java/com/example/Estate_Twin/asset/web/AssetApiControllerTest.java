package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class AssetApiControllerTest {
    @Autowired
    private AssetApiController assetApiController;
    @Test
    public void getAsset() throws Exception {
        ResponseEntity<AssetResponseDto> responseEntity = assetApiController.getAsset(1L);
        log.info(responseEntity.getBody().toString());
    }


}
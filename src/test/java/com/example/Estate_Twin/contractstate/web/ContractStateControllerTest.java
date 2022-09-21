package com.example.Estate_Twin.contractstate.web;

import com.amazonaws.util.IOUtils;
import com.example.Estate_Twin.asset.web.AssetApiController;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.checklist.web.CheckListApiController;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateUpdateRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class ContractStateControllerTest {
    @Autowired
    private ContractStateController contractStateController;
    @Test
    public void getContractState() throws Exception {
            ResponseEntity<List<ContractStateResponseDto>> responseEntity = contractStateController.getContractStateList(6L);
            log.info(responseEntity.getBody().toString());
    }

    @Test
    public void updateContractState() throws Exception {
        ContractStateUpdateRequestDto contractStateUpdateRequestDto = new ContractStateUpdateRequestDto(State.CONTRACT_DOING.toString());
        ResponseEntity<ContractStateResponseDto> responseEntity = contractStateController.updateContractState(6L, contractStateUpdateRequestDto);
        log.info(responseEntity.getBody().toString());
    }

}
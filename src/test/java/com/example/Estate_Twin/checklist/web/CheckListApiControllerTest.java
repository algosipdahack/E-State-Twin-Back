package com.example.Estate_Twin.checklist.web;

import com.amazonaws.util.IOUtils;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
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
/*
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class CheckListApiControllerTest {
    @Autowired
    private CheckListApiController checkListApiController;
    @Test
    public void uploadFile() throws Exception {

        //DTO 생성
        List<MediaSaveMultipartRequestDto> mediaSaveMultipartRequestDtos = new ArrayList<>();
        // Images
        String[] ImageFiles = {"/Users/mincho/Downloads/photo.png", "/Users/mincho/Downloads/image.png"};

        // Image 생성
        for (int i = 0; i < ImageFiles.length; i++) {
            File imageFile = new File(ImageFiles[i]);
            FileInputStream inputStream = new FileInputStream(imageFile);
            MultipartFile multipartFile = new MockMultipartFile(imageFile.getName(), imageFile.getName(), "image/jpg", IOUtils.toByteArray(inputStream));

            MediaSaveMultipartRequestDto mediaSaveMultipartRequestDto = new MediaSaveMultipartRequestDto();
            mediaSaveMultipartRequestDto.setImageFile(multipartFile);

            mediaSaveMultipartRequestDtos.add(mediaSaveMultipartRequestDto);
        }

        String str = "2022-06-12 23:44:54.247";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(str,formatter);
        CheckListSaveRequestDto checkListSaveRequestDto = new CheckListSaveRequestDto().builder()
                .flawPart("1하자부위")
                .checkListContent("1")
                .repairDate(dateTime)
                .repairType("REPAIR")
                .brokerConfirmYN(false)
                .tanentConfirmYN(false)
                .ownerConfirmYN(false)
                .checkListPhotos(mediaSaveMultipartRequestDtos)
                .build();
        ResponseEntity<CheckListResponseDto> responseEntity = checkListApiController.saveCheckList(1L,checkListSaveRequestDto);
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void getChecklist() {
        ResponseEntity<CheckListResponseDto> responseEntity = checkListApiController.getCheckList(1L);
        log.info(responseEntity.getBody().toString());

    }

    @Test
    public void getChecklistbyAsset() {
        ResponseEntity<List<CheckListResponseDto>> responseEntity = checkListApiController.getCheckListbyAsset(1L);
        log.info(responseEntity.getBody().toString());

    }

*/
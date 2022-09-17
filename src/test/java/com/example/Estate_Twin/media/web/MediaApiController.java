package com.example.Estate_Twin.media.web;

import com.example.Estate_Twin.controller.ControllerTest;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MediaApiController {
    private MockMvc mockMvc;
    @Test
    public void uploadFile() throws Exception {
        MockMultipartFile multipartFile1 = new MockMultipartFile("file","test.txt","text/plain","test file".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile multipartFile2 = new MockMultipartFile("file","test2.txt","text/plain","test file2".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart("/api/estate/")
                        .file(multipartFile1)
                        .file(multipartFile2)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
}

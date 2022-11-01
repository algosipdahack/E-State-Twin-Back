package com.example.Estate_Twin.user.controller;

import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.redis.RedisService;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.impl.OAuthService;
import com.example.Estate_Twin.user.service.impl.UserServiceImpl;
import com.example.Estate_Twin.user.web.UserController;
import com.example.Estate_Twin.user.web.dto.UserInfoDto;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {UserController.class})
@ActiveProfiles("test")
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider tokenProvider;
    @MockBean
    UserServiceImpl userService;

    @MockBean
    OAuthService oAuthService;

    @MockBean
    RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    User user;

    @BeforeEach
    void setting() {
        String date = "2022-10-22";
        user = User.builder()
                .borough("강남구")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("조소연")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYW93bGxAbmF2ZXIuY29tIiwidXNlcm5hbWUiOiJkYW93bGxAbmF2ZXIuY29tIiwiaWF0IjoxNjY1NTkwOTc4LCJleHAiOjIyNzAzOTA5Nzh9.Fp0SBQbHo5Uz5WRmazWBPg3vLH30YmOEqn6bHc06m3w6gEQZfpXyizT0PSDJXQRSAv8pU_LzbYyvlquBmDkTog")
                .build();
    }

    @DisplayName("[Post] 집주인/세입자 회원가입")
    @WithMockUser(username = "sophia5460@naver.com", roles = {"USER","ADMIN"})
    @Test
    void 회원가입() throws Exception{
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .birthday(user.getBirthday())
                .borough(user.getBorough())
                .estateType(user.getEstateType().toString())
                .phone(user.getPhone())
                .build();

        //given
        given(userService.signUp(user, userSignUpDto))
                .willReturn(UserInfoDto.builder()
                        .user(user)
                        .build());

        String content = objectMapper.writeValueAsString(userSignUpDto);
        //when
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/signup")
                        .header("X-AUTH-TOKEN", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYW93bGxAbmF2ZXIuY29tIiwidXNlcm5hbWUiOiJkYW93bGxAbmF2ZXIuY29tIiwiaWF0IjoxNjY1NTkwOTc4LCJleHAiOjE2NjkxOTA5Nzh9.aJx3mqsB-3MwzayYDtqtjEgGlvfMF4cbVZAcdzVBdT6KX3mVuX78UR5ohC8fKgODeC-foOZk4tyBBkJZUQmDJQ")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}

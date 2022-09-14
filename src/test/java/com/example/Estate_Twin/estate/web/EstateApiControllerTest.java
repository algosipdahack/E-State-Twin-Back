package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.service.impl.AddressServiceImpl;
import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import com.example.Estate_Twin.auth.jwt.JwtAuthenticationFilter;
import com.example.Estate_Twin.config.SecurityConfig;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateUpdateRequestDto;
import com.example.Estate_Twin.controller.ControllerTest;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.service.impl.EstateServiceImpl;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.service.impl.HouseServiceImpl;
import com.example.Estate_Twin.house.web.HouseApiController;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
        (controllers = HouseApiController.class,
                excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
                                SecurityConfig.class),
                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =
                                JwtAuthenticationFilter.class)
                })
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstateApiControllerTest extends ControllerTest {
    @MockBean
    EstateServiceImpl estateService;
    @MockBean
    HouseServiceImpl houseService;
    @MockBean
    AddressServiceImpl addressService;

    EstateSaveRequestDto estateSaveRequestDto;
    AddressSaveRequestDto addressSaveRequestDto;
    AddressUpdateRequestDto addressUpdateRequestDto;
    ContractStateUpdateRequestDto contractStateUpdateRequestDto;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void 설정() {
        addressSaveRequestDto = new AddressSaveRequestDto()
                .builder()
                .city("경기도")
                .borough("고양시")
                .town("화정동")
                .complexName("6단지")
                .block("123동")
                .unit("3455호")
                .roadName("화정로 27")
                .mainBuildingNumber(234)
                .subBuildingNumber(556)
                .buildingName("00빌딩")
                .build();
        addressUpdateRequestDto = new AddressUpdateRequestDto(addressSaveRequestDto.getCity(), addressSaveRequestDto.getBorough(), addressSaveRequestDto.getTown(), addressSaveRequestDto.getComplexName(),
                addressSaveRequestDto.getBlock(), addressSaveRequestDto.getUnit(), addressSaveRequestDto.getRoadName(), addressSaveRequestDto.getMainBuildingNumber(),
                addressSaveRequestDto.getSubBuildingNumber(), addressSaveRequestDto.getBuildingName());
        contractStateUpdateRequestDto = new ContractStateUpdateRequestDto()
                .builder()
                .state(State.AFTER)
                .build();
        estateSaveRequestDto = new EstateSaveRequestDto()
                .builder()
                .transactionType("MONTHLYRENT")
                .estateThumbNail("썸네일 SRC")
                .content("내용내용")
                .model("모델src")
                .address(addressSaveRequestDto)
                .contractState(contractStateUpdateRequestDto)
                .build();
    }

    // post(http://localhost:8080/api/estate/detail/{houseId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("Estate 데이터 생성 테스트")
    public void 매물만들기() throws Exception {
        //given
        String content = objectMapper.writeValueAsString(estateSaveRequestDto);
        Long houseId = 2L;
        //when
        mockMvc.perform(
                        post("/api/estate/detail/{houseId}",houseId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andDo(print());

    }

    // get(http://localhost:8080/api/estate/detail/{estateId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("Estate 데이터 반환 테스트")
    public void 매물가져오기() throws Exception {
        Long estateId = 2L;
        mockMvc.perform(
                        get("/api/estate/detail/{estateId}",estateId)
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // put(http://localhost:8080/api/estate/detail/{estateId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("Estate 데이터 수정 테스트")
    public void 매물수정하기() throws Exception {
        Long estateId = 2L;
        EstateUpdateRequestDto estateUpdateRequestDto = new EstateUpdateRequestDto(estateSaveRequestDto.getTransactionType().toString(),
                estateSaveRequestDto.getEstateThumbNail(), estateSaveRequestDto.getContent(), estateSaveRequestDto.getModel(),
        estateSaveRequestDto.getEstateThumbNail(), addressUpdateRequestDto);
        String content = objectMapper.writeValueAsString(estateUpdateRequestDto);
        mockMvc.perform(
                        put("/api/estate/detail/{estateId}",estateId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
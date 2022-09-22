package com.example.Estate_Twin.house.web;

import com.example.Estate_Twin.auth.jwt.JwtAuthenticationFilter;
import com.example.Estate_Twin.config.SecurityConfig;
import com.example.Estate_Twin.controller.ControllerTest;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.service.impl.HouseServiceImpl;
import com.example.Estate_Twin.house.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
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
public class HouseApiControllerTest extends ControllerTest {
    @MockBean
    HouseServiceImpl houseService;
    House house;
    HouseSaveRequestDto houseSaveRequestDto;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void 설정() {
        house = new House().builder()
                .bathCount(1L)
                .usageAvailableDate(LocalDateTime.now())
                .roomCount(1L)
                .household(1L)
                .estateType(EstateType.APARTMENT)
                .heatType("heattype")
                .size(1L)
                .moveInAvailableDate(LocalDateTime.now())
                .parkingFee(1L)
                .parking(true)
                .rentableArea(1L)
                .netRentableArea(1L)
                .itemsIncludedMaintenanceFee("fee")
                .maintenanceFee(1L)
                .shortTermRent(true)
                .totalFloors(1L)
                .currentFloors(1L)
                .sellingFee(1L)
                .monthlyRent(1L)
                .deposit(1L)
                .monthlyRent(1L)
                .build();
        house.setId(1L);

        houseSaveRequestDto = HouseSaveRequestDto.builder()
                .bathCount(1L)
                .usageAvailableDate(LocalDateTime.now())
                .roomCount(1L)
                .household(1L)
                .estateType(EstateType.APARTMENT)
                .heatType("heattype")
                .size(1L)
                .moveInAvailableDate(LocalDateTime.now())
                .parkingFee(1L)
                .parking(true)
                .rentableArea(1L)
                .netRentableArea(1L)
                .itemsIncludedMaintenanceFee("fee")
                .maintenanceFee(1L)
                .shortTermRent(true)
                .totalFloors(1L)
                .currentFloors(1L)
                .sellingFee(1L)
                .monthlyRent(1L)
                .deposit(1L)
                .monthlyRent(1L)
                .build();
    }

    // post(http://localhost:8080/api/house)
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("House 데이터 생성 테스트")
    public void 집만들기() throws Exception {
        given(houseService.saveHouse(houseSaveRequestDto))
                .willReturn(new HouseResponseDto(house));
        //given
        String content = objectMapper.writeValueAsString(houseSaveRequestDto);

        //when
        mockMvc.perform(
                post("/api/house")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deposit").exists())
                .andDo(print());
        verify(houseService).saveHouse(houseSaveRequestDto);

    }

    // get(http://localhost:8080/api/house/{houseId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("House 데이터 반환 테스트")
    public void 집가져오기() throws Exception {
        given(houseService.getHouse(1L))
                .willReturn(new HouseResponseDto(house));
        Long houseId = 1L;
        mockMvc.perform(
                get("/api/house/{houseId}",houseId)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(houseService).getHouse(1L);
    }

    // put(http://localhost:8080/api/house/{houseId})
    @Test
    @WithMockUser(roles="USER")
    @DisplayName("House 데이터 수정 테스트")
    public void 집수정하기() throws Exception {

        Long houseId = 1L;
        HouseUpdateRequestDto houseUpdateRequestDto = new HouseUpdateRequestDto(house.getDeposit(),house.getMonthlyRent(),house.getSellingFee(),house.getCurrentFloors(),
                house.getTotalFloors(),house.isShortTermRent(),house.getMaintenanceFee(),house.getItemsIncludedMaintenanceFee(),
                house.getNetRentableArea(),house.getRentableArea(),house.isParking(),house.getParkingFee(),house.getMoveInAvailableDate(),
                house.getUsageAvailableDate(),house.getSize(),house.getHeatType(),house.getEstateType(),house.getHousehold(),
                house.getRoomCount(),house.getBathCount());
        String content = objectMapper.writeValueAsString(houseUpdateRequestDto);

        mockMvc.perform(
                        put("/api/house/{houseId}",houseId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deposit").exists())
                .andDo(print());
    }

}
*/

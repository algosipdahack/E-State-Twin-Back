package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.config.TestConfig;
import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.web.dto.EstateDetailDto;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@DataJpaTest
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@ActiveProfiles("prod")
public class EstateRepositoryTest {
    @Autowired
    private EstateRepository estateRepository;
    @Autowired
    private PreferEstateRepository preferEstateRepository;
    @Test
    void getTest() {
        //given
        //when
        //List<EstateListResponseDto> estateListResponseDtoList = preferEstateRepository.findByUserIdAndPrefer(10L, Preference.RECENT, PageRequest.of(0,10));
        //then
        /*Assertions.assertThat(estateDetailDto.getBroker().getId())
                .isEqualTo(1L);
        Assertions.assertThat(estateDetailDto.getHouse().getId())
                .isEqualTo(11L);
        Assertions.assertThat(estateDetailDto.getEstatehit().getTotalHit())
                .isEqualTo(97L);
        System.out.println(estateDetailDto.getAssets());*/
    }
}

package com.example.Estate_Twin;

import com.example.Estate_Twin.domain.estate.Estate;
import com.example.Estate_Twin.domain.estate.EstateRepository;
import com.example.Estate_Twin.domain.estate.Rank;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static com.example.Estate_Twin.domain.estate.Rank.RANK_MANY;

@SpringBootTest
class EStateTwinApplicationTests {

	@Autowired
	EstateRepository estateRepository;

	@After
	public void cleanup(){
		estateRepository.deleteAll();
	}

	@Test
	public void 매물정보_불러오기() {
		//given
		String content = "내용";
		Rank rank =  RANK_MANY;
		String model = "src";
		String arCam = "srcc";

		estateRepository.save(Estate.builder()
				.content(content)
				.rank(rank)
				.model(model)
				.arCam(arCam)
				.build()
		);
		//when
		List<Estate> estates = estateRepository.findAll();

		//then
		Estate estate = estates.get(0);
		assertThat(estate.getArCam()).isEqualTo(arCam);


	}

}

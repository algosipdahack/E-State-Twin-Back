package com.example.Estate_Twin.config;

import com.example.Estate_Twin.domain.estate.EstateNoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EstateNoRepository estateNoRepository;


    @Bean
    public Job job() {
        Job job = jobBuilderFactory.get("job")
                .start(step())
                .build();
        return job;
    }
    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Step!");
                    estateNoRepository.
                })
                .build();
    }


}

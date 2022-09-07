package com.example.Estate_Twin.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.*;

import javax.persistence.*;

@Configuration
public class QueryDSLConfiguration {
    @PersistenceContext
    EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}

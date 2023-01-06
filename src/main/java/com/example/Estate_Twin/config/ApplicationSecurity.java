package com.example.Estate_Twin.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Profile("test")
@Configuration
public class ApplicationSecurity {
    @Bean
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        http.requestMatchers(matchers -> matchers.antMatchers("/**"))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .csrf().disable()
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();
        return http.build();
    }
}

package com.example.Estate_Twin.config;

import com.example.Estate_Twin.auth.jwt.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
@Profile({"prod","server"})
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private static final String[] ignores = {
            "/favicon.ico",
            "/error",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/api/home/**",
            "/api/user-auth/**"
    };

    @Order(0)
    @Bean
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        http.requestMatchers(matchers -> matchers.antMatchers(ignores))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .csrf().disable()
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();
        return http.build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

package com.example.Estate_Twin.config;

import com.example.Estate_Twin.auth.OAuth2FailureHandler;
import com.example.Estate_Twin.auth.jwt.*;
import com.example.Estate_Twin.auth.repository.CookieAuthorizationRequestRepository;
import com.example.Estate_Twin.auth.service.CustomOAuth2UserService;
import com.example.Estate_Twin.auth.OAuth2SuccessHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Profile("prod")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final OAuth2FailureHandler failureHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    //401 error
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    //403 error
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("1111");
        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()

                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/oauth2/**","/auth/**").permitAll()
                .anyRequest().permitAll()

                .and()
                .formLogin().disable()
                .oauth2Login()
                .authorizationEndpoint() // front -> back으로 요청 보내는 URL
                .baseUri("/oauth2/authorize") // ex) /oauth2/authorize/google
                .authorizationRequestRepository(cookieAuthorizationRequestRepository)

                .and()
                .redirectionEndpoint() //Authorization code와 함께 리다이렉트할 URL  ex) /login/oauth2/code/google

                .and()
                .userInfoEndpoint() // Provider로부터 획득한 유저정보를 다룰 service class를 지정
                .userService(customOAuth2UserService)

                .and()
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/swagger-ui/index.html");
    }

}

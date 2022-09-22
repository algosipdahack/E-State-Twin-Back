package com.example.Estate_Twin.auth.jwt;

import com.example.Estate_Twin.auth.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = tokenProvider.resolveToken(servletRequest);

        if (tokenProvider.validateToken(token)) {
            try {
                setAuthToSecurityContextHolder(token);
            } catch (Exception e) {
                log.error("토큰에 해당하는 사용자가 없습니다.", e);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setAuthToSecurityContextHolder(String token) {
        Authentication auth = tokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
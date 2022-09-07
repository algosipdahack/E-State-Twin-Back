package com.example.Estate_Twin.auth;

import com.example.Estate_Twin.auth.jwt.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
       if(response.isCommitted()) {
           log.debug("Response has already been committed");
           return;
       }

        Token token = tokenProvider.createToken(authentication);
        writeTokenResponse(response,token);
    }

    //response에다가 token을 담아서 줌
    private void writeTokenResponse(HttpServletResponse response, Token token) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("Access",token.getAccessToken());
        response.addHeader("Refresh",token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");
        //응답 스트림에 텍스트를 기록하기 위함
        PrintWriter out = response.getWriter();
        //스트림에 텍스트를 기록
        out.println(objectMapper.writeValueAsString(token));
        out.flush();
    }
}

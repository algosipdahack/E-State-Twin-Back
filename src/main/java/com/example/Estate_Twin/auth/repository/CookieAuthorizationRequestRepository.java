package com.example.Estate_Twin.auth.repository;

import com.example.Estate_Twin.auth.util.CookieUtil;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CookieAuthorizationRequestRepository implements AuthorizationRequestRepository {
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int COOKIE_EXPIRE_SECONDS = 180;

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request,response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
    }

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookieUtil.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookieUtil.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }
    private void writeTokenResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        //응답 스트림에 텍스트를 기록하기 위함
    }
    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if(authorizationRequest == null) {
            CookieUtil.deleteCookie(request,response,OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
            return;
        }
        CookieUtil.addCookie(response,OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,CookieUtil.serialize(authorizationRequest),COOKIE_EXPIRE_SECONDS);
        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtil.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, COOKIE_EXPIRE_SECONDS);
        }
        try {
            writeTokenResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //response에다가 token을 담아서 줌

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }
}

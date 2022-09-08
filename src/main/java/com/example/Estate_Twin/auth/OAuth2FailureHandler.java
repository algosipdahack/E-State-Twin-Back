package com.example.Estate_Twin.auth;

import com.example.Estate_Twin.auth.repository.CookieAuthorizationRequestRepository;
import com.example.Estate_Twin.auth.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final CookieAuthorizationRequestRepository authorizationRequestRepository;
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String targetUrl = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse("/");

        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();

        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}

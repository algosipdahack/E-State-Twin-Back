package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.auth.dto.OAuth2UserInfo;
import com.example.Estate_Twin.auth.jwt.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    private MultiValueMap<String, String> tokenRequest(String code, ClientRegistration provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUri());
        formData.add("client_secret", provider.getClientSecret());
        formData.add("client_id",provider.getClientId());
        return formData;
    }

    //kakao로부터 access token, refresh token 전달 받음
    private OAuth2AccessTokenResponse getToken(String code, ClientRegistration provider) {
        return WebClient.create()
                .post()
                .uri(provider.getProviderDetails().getTokenUri())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(OAuth2AccessTokenResponse.class)
                .block();
    }

    private Map<String, Object> getUserAttributes(ClientRegistration provider, String accessToken) {
        return WebClient.create()
                .get()
                .uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }

    private User createUser(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User user = User.builder()
                .email(oAuth2UserInfo.getEmail())
                .authProvider(authProvider)
                .role(Role.USER)
                .name(oAuth2UserInfo.getName())
                .build();
        return userRepository.save(user);
    }

    @Transactional
    Token getUserProfile(String providerName, String accessToken, ClientRegistration provider) {
        //resource server로부터 유저 정보 얻어옴
        Map<String, Object> userAttributes = getUserAttributes(provider, accessToken);
        String userNameAttributeName = provider.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuth2UserInfo attributes = OAuth2UserInfo.of(providerName.toUpperCase(), userNameAttributeName, userAttributes);

        if(attributes.getEmail().isEmpty()) {
            throw new CheckHouseException(ErrorCode.EMAIL_NOT_FOUND_FROM_PROVIDER);
        }

        Optional<User> userOptional = userRepository.findByEmail(attributes.getEmail());
        User user;
        boolean isMember;
        // 이미 가입된 경우
        if(userOptional.isPresent()) {
            user = userOptional.get();
            isMember = true;
            // 탈퇴한 회원인 경우
            if(user.isUserDel()) {
                isMember = false;
                user.setUserDelFalse();
            }
            if(AuthProvider.valueOf(providerName) != user.getAuthProvider()) {
                throw new CheckHouseException(ErrorCode.NOT_MATCHED_AUTH_PROVIDER);
            }
        } else {
            //첫 로그인인 경우
            isMember = false;
            user = createUser(attributes,AuthProvider.valueOf(providerName));
        }

        CustomUserDetails.create(user,userAttributes);
        String jAccessToken = tokenProvider.createAccessToken(user);
        String jRefreshToken = tokenProvider.createRefreshToken(user);

        user.setRefreshToken(jRefreshToken);
        return new Token(jAccessToken,jRefreshToken,isMember);
    }

}

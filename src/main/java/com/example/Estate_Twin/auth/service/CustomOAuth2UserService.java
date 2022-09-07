package com.example.Estate_Twin.auth.service;

import com.example.Estate_Twin.auth.dto.OAuth2UserInfo;
import com.example.Estate_Twin.exception.OAuthProcessingException;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        return process(userRequest,oAuth2User);
    }
    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo attributes = OAuth2UserInfo.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        if(attributes.getEmail().isEmpty()) {
            throw new OAuthProcessingException("Email not found from OAuth2 provider");
        }


        Optional<User> userOptional = userRepository.findByEmail(attributes.getEmail());
        User user;

        //이미 가입된 경우
        if(userOptional.isPresent()){
            user = userOptional.get();
            if(AuthProvider.valueOf(registrationId) != user.getAuthProvider()) {
                throw new OAuthProcessingException("Wrong Match Auth Provider");
            }
        } else {
            //첫 로그인인 경우
            user = createUser(attributes,AuthProvider.valueOf(registrationId));
        }
        return CustomUserDetails.create(user,oAuth2User.getAttributes());
    }

    private User createUser(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        User user = User.builder()
                .email(oAuth2UserInfo.getEmail())
                .authProvider(authProvider)
                .role(Role.TANENT)
                .name(oAuth2UserInfo.getName())
                .build();
        return userRepository.save(user);
    }
}

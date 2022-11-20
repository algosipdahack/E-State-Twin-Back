package com.example.Estate_Twin.config;

import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser withUser) {
            User user = User.builder()
                .email(withUser.email())
                .authProvider(AuthProvider.KAKAO)
                .role(Role.USER)
                .name(withUser.name())
                .build();
            CustomUserDetails principal = CustomUserDetails.create(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principal, "", principal.getAuthorities());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            return context;
    }
}


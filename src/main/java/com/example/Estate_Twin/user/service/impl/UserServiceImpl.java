package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.user.domain.dao.impl.UserDAOImpl;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAO;
    @Override
    public UserResponseDto getUserbyId(Long id) {
        return new UserResponseDto(userDAO.findUserById(id));
    }

    @Override
    public UserResponseDto getUserbyEmail(String email) {
        return new UserResponseDto(userDAO.findUserByEmail(email));
    }

    @Override
    public UserInfoDto signUp(String email, UserSignUpDto userSignUpDto) {
        return new UserInfoDto(userDAO.signUp(email, userSignUpDto));
    }

    /*public Long deleteMember(String email) {
        userDAO.
    }
    public Long logout(){
        @Transactional
        public void logout(HttpServletRequest request) {

            // accessToken redisTemplate 블랙리스트 추가
            String jwt = request.getHeader("Authorization").substring(7);
            ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();
            logoutValueOperations.set(jwt, jwt); // redis set 명령어

            // refreshToken 삭제
            refreshTokenRepository.deleteByKey(String.valueOf(SecurityUtil.getLoginMemberId()))
                    .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        }
        SecurityContextHolder.clearContext();
    }*/
}

package com.woori.wooribackoffice.security.service;

import com.woori.wooribackoffice.domain.entity.CurrentToken;
import com.woori.wooribackoffice.domain.entity.User;
import com.woori.wooribackoffice.repository.CurrentTokenRepository;
import com.woori.wooribackoffice.security.model.dto.request.LoginRequest;
import com.woori.wooribackoffice.security.util.CurrentUserUtils;
import com.woori.wooribackoffice.security.util.JwtTokenUtils;
import com.woori.wooribackoffice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final CurrentTokenRepository currentTokenRepository;
    private final CurrentUserUtils currentUserUtils;

    // db 에 있는 사용자 정보와 비교하여, 로그인 구현
    @Transactional
    public String createToken(LoginRequest loginRequest) {
        User user = userService.findByName(loginRequest.getUsername()); // 이름 기반으로 사용자 정보 가져옴

        // 패스워드 맞는지 확인
        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        // 비활성화 유저인지 확인
        if (!user.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }

        String token = JwtTokenUtils.createToken(user, loginRequest.getRememberMe()); // 사용자 정보 기반으로 토큰 생성

        currentTokenRepository.save(new CurrentToken()
                .setToken(token)
                .setUserId(user.getId())); // 토큰을 redis 에 저장해놓음

        return token;
    }

    @Transactional
    public void removeToken() {
        currentTokenRepository.deleteByUserId(currentUserUtils.getCurrentUser().getId());
    }
}

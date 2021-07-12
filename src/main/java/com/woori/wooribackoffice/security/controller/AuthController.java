package com.woori.wooribackoffice.security.controller;

import com.woori.wooribackoffice.security.constant.SecurityConstants;
import com.woori.wooribackoffice.security.model.dto.request.LoginRequest;
import com.woori.wooribackoffice.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.createToken(loginRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, token);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.removeToken();
        return ResponseEntity.ok().build();
    }
}

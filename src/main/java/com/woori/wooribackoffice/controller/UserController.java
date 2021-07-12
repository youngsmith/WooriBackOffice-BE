package com.woori.wooribackoffice.controller;

import com.woori.wooribackoffice.domain.dto.request.UserRegisterRequest;
import com.woori.wooribackoffice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    //@PostMapping
//    public ResponseEntity<Void> signUp(@RequestBody UserRegisterRequest userRegisterRequest) {
//        userService.save(userRegisterRequest);
//        return ResponseEntity.ok().build();
//    }
}

package com.woori.wooribackoffice.security.controller;

import com.woori.wooribackoffice.security.service.UserService;
import lombok.RequiredArgsConstructor;
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

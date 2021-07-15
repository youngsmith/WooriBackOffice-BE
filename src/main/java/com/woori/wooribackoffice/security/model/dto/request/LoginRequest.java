package com.woori.wooribackoffice.security.model.dto.request;

import lombok.*;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}

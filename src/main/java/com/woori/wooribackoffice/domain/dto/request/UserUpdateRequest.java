package com.woori.wooribackoffice.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String userName;
    private String password;
    private Boolean enabled;
}

package com.woori.wooribackoffice.security.model.dto.request;


import com.woori.wooribackoffice.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String username;
    private String password;

    public User toUser() {
        return new User()
                .setUsername(this.getUsername())
                .setEnabled(true);
    }
}

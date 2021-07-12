package com.woori.wooribackoffice.domain.dto.request;


import com.woori.wooribackoffice.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String userName;
    private String password;

    public User toUser() {
        return new User()
                .setUserName(this.getUserName())
                .setEnabled(true);
    }
}

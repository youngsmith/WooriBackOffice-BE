package com.woori.wooribackoffice.security.util;

import com.woori.wooribackoffice.domain.entity.User;
import com.woori.wooribackoffice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CurrentUserUtils {
    private final UserService userService;

    public User getCurrentUser() {
        return userService.findByName(getCurrentUserName());
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}

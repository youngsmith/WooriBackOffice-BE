package com.woori.wooribackoffice.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description AuthenticationEntryPoint
 * Used to solve the exception when anonymous users access resources that require permissions to access
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * When a user tries to access a REST resource that requires permission and does not provide a Token or
     * the Token is wrong or expired, this method will be called to send a 401 response and an error message
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}

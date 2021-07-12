package com.woori.wooribackoffice.security.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description AccessDeineHandler Used to solve the exception when the authenticated user accesses the resource without permission
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * When a user tries to access a REST resource that requires permission and the permission is insufficient,
     * this method will be called to send a 403 response and an error message
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        accessDeniedException = new AccessDeniedException("Sorry you don not enough permissions to access it!");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }
}

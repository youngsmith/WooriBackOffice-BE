package com.woori.wooribackoffice.security.filter;

import com.woori.wooribackoffice.domain.entity.CurrentToken;
import com.woori.wooribackoffice.repository.CurrentTokenRepository;
import com.woori.wooribackoffice.security.constant.SecurityConstants;
import com.woori.wooribackoffice.security.util.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description The filter processes all HTTP requests and
 * checks whether there is an Authorization header with the correct token.
 * For example, if the token has not expired or the signing key is correct.
 *
 * @WebFilter + @ServletComponentScan
 * https://taetaetae.github.io/2020/04/06/spring-boot-filter/
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final CurrentTokenRepository currentTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.TOKEN_HEADER); // 헤더에서 키값을 통해 토큰 추출
        // 토큰 기본 검사
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            log.info("token is not available");
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }

        String tokenWithOutPrefix = token.replace(SecurityConstants.TOKEN_PREFIX, ""); // 토큰 prefix 제거
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            // redis 에 저장되어 있는 이전 토큰 값과 비교하여 다르면 리턴
            String previousToken = currentTokenRepository.findByUserId(Long.parseLong(JwtTokenUtils.getId(tokenWithOutPrefix)))
                    .orElse(CurrentToken.EMPTY)
                    .getToken();

            if (!token.equals(previousToken)) {
                log.info("current token does not equal with previous token, or previous token does not exist");
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }
            authentication = JwtTokenUtils.getAuthentication(tokenWithOutPrefix);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


}



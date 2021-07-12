package com.woori.wooribackoffice.security.configuration;

import com.woori.wooribackoffice.repository.CurrentTokenRepository;
import com.woori.wooribackoffice.security.constant.SecurityConstants;
import com.woori.wooribackoffice.security.exception.JwtAccessDeniedHandler;
import com.woori.wooribackoffice.security.exception.JwtAuthenticationEntryPoint;
import com.woori.wooribackoffice.security.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

/*
    Spring Method Security
    Spring Security supports authorization semantics at the method level.
    Typically, we could secure our service layer by, for example,
    restricting which roles are able to execute a particular method –
    and test it using dedicated method-level security test support.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CurrentTokenRepository currentTokenRepository;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                // Disable CSRF
                .csrf().disable()
                .authorizeRequests()
                // All other interfaces need to be authenticated before they can be requested
                .anyRequest().authenticated()
                .and()
                // Add custom filter : https://kangwoojin.github.io/programing/spring-security-basic-add-custom-filter/
                .addFilterBefore(new JwtAuthorizationFilter(currentTokenRepository), BasicAuthenticationFilter.class)
                // No session required (no session created)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Authorization exception handling
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler());
        // Prevent the Frame of H2 web page from being intercepted
        // http.headers().frameOptions().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList(SecurityConstants.TOKEN_HEADER));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/auth/*");
    }
}

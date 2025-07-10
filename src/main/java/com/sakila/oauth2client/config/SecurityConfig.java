package com.sakila.oauth2client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.sakila.oauth2client.service.CustomOAuth2Service;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2Service customOAuth2Service;

    public SecurityConfig(CustomOAuth2Service customOAuth2Service) {
        this.customOAuth2Service = customOAuth2Service;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/WEB-INF/view/**", "/login", "/login/**", "/oauth2/**", "/css/**", "/js/**", "/images/**").permitAll()
            .anyRequest().authenticated()
        );

        http.formLogin(form -> form.disable());

        http.oauth2Login(oauth -> oauth
            .loginPage("/login")
            .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2Service))
        );

        http.logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        );

        return http.build();
    }
} 

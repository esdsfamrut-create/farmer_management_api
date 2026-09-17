package com.famrut.farmer_management_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

private final JwtAuthenticationFilter jwtAuthenticationFilter;
private final CustomAuthenticationEntryPoint authenticationEntryPoint;
private final CustomAccessDeniedHandler accessDeniedHandler;

public SecurityConfig(
        JwtAuthenticationFilter jwtAuthenticationFilter,
        CustomAuthenticationEntryPoint authenticationEntryPoint,
        CustomAccessDeniedHandler accessDeniedHandler) {

    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.accessDeniedHandler = accessDeniedHandler;
}

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

               .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )

              .authorizeHttpRequests(auth -> auth

                                // Public APIs
                                .requestMatchers(
                                        "/api/v1/health",
                                        "/api/v1/message",
                                        "/api/v1/auth/**",
                                        "/error"
                                ).permitAll()

                                // FARMER + ADMIN APIs
                                .requestMatchers(
                                        "/api/v1/farmers/**",
                                        "/api/v1/farms/**",
                                        "/api/v1/farm-crops/**",
                                        "/api/v1/states/**",
                                        "/api/v1/districts/**",
                                        "/api/v1/sub-districts/**",
                                        "/api/v1/blocks/**",
                                        "/api/v1/villages/**",
                                        "/api/v1/crops/**",
                                        "/api/v1/languages/**"
                                ).hasAnyRole("FARMER", "ADMIN")

                                // Unknown URLs are allowed through Security
                                // so Spring MVC can return 404
                                .anyRequest().permitAll()
                        )
                

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
package com.duoc.learningplatform.evaluacion_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // ACTIVIDADES
                .requestMatchers(HttpMethod.POST, "/api/actividades/**").hasRole("PROFESOR")
                .requestMatchers(HttpMethod.DELETE, "/api/actividades/**").hasRole("PROFESOR")

                // EVALUACIONES
                .requestMatchers(HttpMethod.POST, "/api/evaluaciones/**").hasRole("ALUMNO")

                .requestMatchers(HttpMethod.PUT, "/api/evaluaciones/**").hasRole("PROFESOR")

                // CONSULTAS (AMBOS ROLES)
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ALUMNO", "PROFESOR")

                .anyRequest().authenticated()
            )

            .addFilterBefore(jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
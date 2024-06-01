package com.codigo.clinica.mspaciente.infrastructure.config;

import com.codigo.clinica.mspaciente.infrastructure.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/ms-patient/contact/create").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/ms-patient/record/create").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/ms-patient/patient/create").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/ms-patient/teatment/create").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/ms-patient/**").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

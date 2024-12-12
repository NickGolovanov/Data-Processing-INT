package com.example.nefix.authentification.config;

import com.example.nefix.authentification.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.nefix.authentification.user.Permission.*;
import static com.example.nefix.authentification.user.Role.MEDIOR;
import static com.example.nefix.authentification.user.Role.SENIOR;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration
{
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("api/v1/management/**").hasAnyRole(SENIOR.name(), MEDIOR.name())

                                .requestMatchers(GET, "api/v1/management/**").hasAnyAuthority(SENIOR_READ.name(), MEDIOR_READ.name())
                                .requestMatchers(POST, "api/v1/management/**").hasAnyAuthority(SENIOR_CREATE.name(), MEDIOR_CREATE.name())
                                .requestMatchers(PUT, "api/v1/management/**").hasAnyAuthority(SENIOR_UPDATE.name(), MEDIOR_UPDATE.name())
                                .requestMatchers(DELETE, "api/v1/management/**").hasAnyAuthority(SENIOR_DELETE.name(), MEDIOR_DELETE.name())

                                .requestMatchers("api/v1/senior/**").hasRole(SENIOR.name())

                                .requestMatchers(GET, "api/v1/senior/**").hasAuthority(SENIOR_READ.name())
                                .requestMatchers(POST, "api/v1/senior/**").hasAuthority(SENIOR_CREATE.name())
                                .requestMatchers(PUT, "api/v1/senior/**").hasAuthority(SENIOR_UPDATE.name())
                                .requestMatchers(DELETE, "api/v1/senior/**").hasAuthority(SENIOR_DELETE.name())

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

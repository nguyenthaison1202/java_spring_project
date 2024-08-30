package com.mockproject.group3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        private static final String[] PUBLIC_ENPOINT = { "/**" };

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeHttpRequests(request -> request
                                .requestMatchers(HttpMethod.POST, PUBLIC_ENPOINT).permitAll()
                                .requestMatchers(HttpMethod.GET, PUBLIC_ENPOINT).permitAll()
                                .requestMatchers(HttpMethod.PUT, PUBLIC_ENPOINT).permitAll()
                                .requestMatchers(HttpMethod.DELETE, PUBLIC_ENPOINT).permitAll()
                                .anyRequest().authenticated())
                                .csrf(AbstractHttpConfigurer::disable)
                                .exceptionHandling(handling -> handling
                                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

                return httpSecurity.build();
        }
}

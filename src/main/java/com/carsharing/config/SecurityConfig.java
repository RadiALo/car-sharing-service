package com.carsharing.config;

import com.carsharing.security.jwt.JwtTokenFilter;
import com.carsharing.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        "/health-check",
                                        "/login",
                                        "/register",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**",
                                        "/payments/success",
                                        "/payments/cancel",
                                        "/inject"
                                ).permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/cars",
                                        "/rentals/{id}/return").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/cars/{id}").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST,
                                        "/rentals",
                                        "/payments").hasAnyRole("CUSTOMER", "MANAGER")
                                .requestMatchers(HttpMethod.GET, "/cars").permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        "users/me",
                                        "/cars/{id}").hasAnyRole("CUSTOMER", "MANAGER")
                                .requestMatchers(HttpMethod.GET,
                                        "/rentals/{id}",
                                        "/rentals/user/{id}").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT,
                                        "/users/{id}/role",
                                        "/cars/{id}").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT,
                                        "/users/me").hasAnyRole("CUSTOMER", "MANAGER")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}

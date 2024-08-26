package com.enesd.myshelfbackend.config;

import com.enesd.myshelfbackend.filters.JwtAuthorizationFilter;
import com.enesd.myshelfbackend.filters.RateLimitFilter;
import com.enesd.myshelfbackend.security.CustomAuthenticationEntryPoint;
import com.enesd.myshelfbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final RateLimitFilter rateLimitFilter;
    private final UserService userService;
    private final CustomAuthenticationEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/*/auth/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(rateLimitFilter, JwtAuthorizationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

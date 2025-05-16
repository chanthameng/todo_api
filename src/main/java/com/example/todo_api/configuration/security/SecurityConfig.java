package com.example.todo_api.configuration.security;
import com.example.todo_api.configuration.jwt.JwtAuthenticationEntryPoint;
import com.example.todo_api.configuration.jwt.JwtRequestFilter;
import com.example.todo_api.service.user.AppUserServiceImp;
import lombok.AllArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AppUserServiceImp appUserService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtRequestFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtAuthEntrypoint;
    private final CorsFilter corsFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // this provider specifies the user details service and password encoder for authentication.
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/todo/v1/auth/**","/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/test/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntrypoint))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }}
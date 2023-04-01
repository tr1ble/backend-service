package com.epam.learn.config;

import com.epam.learn.security.AuthenticationFailureHandler;
import jakarta.servlet.Filter;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Profile("!test")
public class SecurityConfig {

  private final Filter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;
  private final AuthenticationFailureHandler authenticationFailureHandler;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityWebFilterChain(
      HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/register").permitAll()
        .requestMatchers("/user/**").authenticated()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionAuthenticationFailureHandler(authenticationFailureHandler)
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler(
            (request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }

  private CorsConfiguration buildCorsConfiguration() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedHeaders(List.of("Authorization"));
    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
    corsConfiguration.setAllowedMethods(List.of("*"));

    return corsConfiguration;
  }
}

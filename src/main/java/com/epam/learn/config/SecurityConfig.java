package com.epam.learn.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityWebFilterChain(
      HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().configurationSource(request -> buildCorsConfiguration()).and()
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/actuator/**").permitAll()
            .requestMatchers("/user/**").permitAll()
            .requestMatchers("/subscription/**").permitAll()
            .anyRequest()
            .authenticated())
        .httpBasic();

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

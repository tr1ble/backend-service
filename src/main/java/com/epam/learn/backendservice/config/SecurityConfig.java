package com.epam.learn.backendservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityWebFilterChain(
      HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/actuator/**").permitAll()
            .requestMatchers("/user/**").permitAll()
            .anyRequest()
            .authenticated())
        .httpBasic();

    return http.build();
  }
}

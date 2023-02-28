package com.epam.learn.backendservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("DEV")
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityWebFilterChain(
      HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/actuator/**").permitAll()
            .anyRequest()
            .authenticated())
        .httpBasic();

    return http.build();
  }
}

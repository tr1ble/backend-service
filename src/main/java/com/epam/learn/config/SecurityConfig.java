package com.epam.learn.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Profile("!test")
public class SecurityConfig {

  private final Filter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public SecurityFilterChain securityWebFilterChain(
      HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().configurationSource(request -> buildCorsConfiguration()).and()
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/about").permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/registration").permitAll()
            .requestMatchers("/actuator/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("ADMIN")
            .requestMatchers("/role/**").hasRole("ADMIN")
            .requestMatchers("/subscription/**").hasRole("USER")
            .anyRequest()
            .authenticated())
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .permitAll()
        .and()
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

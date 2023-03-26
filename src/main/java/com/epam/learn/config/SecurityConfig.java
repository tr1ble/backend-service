package com.epam.learn.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfig {

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
            .requestMatchers("/").permitAll()
            .requestMatchers("/about").permitAll()
            .requestMatchers("/registration").permitAll()
            .requestMatchers("/actuator/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("ADMIN")
            .requestMatchers("/role/**").hasRole("ADMIN")
            .requestMatchers("/subscription/**").hasRole("USER")
            .anyRequest()
            .authenticated())
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error")
        .permitAll()
        .and()
        .logout()
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .logoutSuccessUrl("/login?logout")
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

package com.epam.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "com.epam.learn")
@EnableWebSecurity
@EntityScan("com.epam.learn")
public class BackendServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendServiceApplication.class, args);
  }
}

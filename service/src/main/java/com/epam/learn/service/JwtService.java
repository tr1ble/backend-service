package com.epam.learn.service;

import java.util.Date;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
  String extractUsername(String token);
  Date extractExpiration(String token);
  boolean isTokenExpired(String token);
  String generateToken(Map<String, Object> claims, UserDetails userDetails);
  String generateToken(UserDetails userDetails);
  boolean isTokenValid(String token, UserDetails userDetails);
}

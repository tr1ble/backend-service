package com.epam.learn.service.impl;

import com.epam.learn.dto.authentication.AuthenticationRequest;
import com.epam.learn.dto.authentication.AuthenticationResponse;
import com.epam.learn.mapper.AuthMapper;
import com.epam.learn.model.Role;
import com.epam.learn.repository.UserRepository;
import com.epam.learn.service.AuthService;
import com.epam.learn.service.JwtService;
import com.epam.learn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthMapper authMapper;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  @Override
  public AuthenticationResponse register(AuthenticationRequest authenticationRequest) {
    var user = authMapper.mapToUser(authenticationRequest);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.USER);
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  @Override
  public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
    String username = authenticationRequest.getUsername();
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            username,
            authenticationRequest.getPassword()
        )
    );
    var user = userService.loadUserByUsername(username);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}

package com.epam.learn.controller;

import com.epam.learn.api.AuthApi;
import com.epam.learn.dto.authentication.AuthenticationRequest;
import com.epam.learn.dto.authentication.AuthenticationResponse;
import com.epam.learn.service.AuthService;
import com.epam.learn.service.SecurityService;
import com.epam.learn.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {
  private final AuthService authService;
  private final SecurityService securityService;

  @Override
  public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
    return authService.login(authenticationRequest);
  }

  @Override
  public AuthenticationResponse registration(AuthenticationRequest authenticationRequest) {
    return authService.register(authenticationRequest);
  }

  @Override
  public List<String> getBlocked() {
    return securityService.getBlockedUsers();
  }
}

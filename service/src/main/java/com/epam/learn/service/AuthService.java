package com.epam.learn.service;

import com.epam.learn.dto.authentication.AuthenticationRequest;
import com.epam.learn.dto.authentication.AuthenticationResponse;

public interface AuthService {
  AuthenticationResponse register(AuthenticationRequest authenticationRequest);
  AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}

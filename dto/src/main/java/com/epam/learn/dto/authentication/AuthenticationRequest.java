package com.epam.learn.dto.authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
  private String username;
  private String password;
}

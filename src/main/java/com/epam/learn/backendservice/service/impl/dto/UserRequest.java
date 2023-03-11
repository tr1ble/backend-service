package com.epam.learn.backendservice.service.impl.dto;

import lombok.Data;

@Data
public class UserRequest {
  private String username;
  private String password;
}

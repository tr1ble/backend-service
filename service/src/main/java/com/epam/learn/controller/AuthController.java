package com.epam.learn.controller;

import com.epam.learn.api.AuthApi;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.service.SecurityService;
import com.epam.learn.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {
  private final UserService userService;
  private final SecurityService securityService;

  @Override
  public UserResponseDto registration(UserRequestDto user) {
    return userService.create(user);
  }

  @Override
  public List<String> getBlocked() {
    return securityService.getBlockedUsers();
  }
}

package com.epam.learn.backendservice.api.controller;

import com.epam.learn.backendservice.api.UserApi;
import com.epam.learn.backendservice.dto.UserRequest;
import com.epam.learn.backendservice.model.User;
import com.epam.learn.backendservice.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @Override
  public User create(UserRequest userRequest) {
    return userService.create(userRequest);
  }

  @Override
  public User get(UUID id) {
    return userService.getById(id);
  }

  @Override
  public void delete(UUID id) {
    userService.deleteById(id);
  }

  @Override
  public User update(User user) {
    return userService.update(user);
  }

  @Override
  public List<User> findAll(List<UUID> ids) {
    return ids == null || ids.isEmpty() ? userService.getAll() : userService.getById(ids);
  }
}

package com.epam.learn.backendservice.controller;

import com.epam.learn.backendservice.dto.UserRequest;
import com.epam.learn.backendservice.model.User;
import com.epam.learn.backendservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@Valid @RequestBody UserRequest userRequest) {
    return userService.create(userRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User get(@PathVariable Long id) {
    return userService.getById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    userService.deleteById(id);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public User update(@Valid @RequestBody User user) {
    return userService.update(user);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<User> findAll(@RequestParam(required = false) List<Long> ids) {
    return ids == null || ids.isEmpty() ? userService.getAll() : userService.getById(ids);
  }
}

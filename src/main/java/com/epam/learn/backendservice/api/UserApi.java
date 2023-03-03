package com.epam.learn.backendservice.api;

import com.epam.learn.backendservice.dto.UserRequest;
import com.epam.learn.backendservice.model.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
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

@RequestMapping("/user")
public interface UserApi {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  User create(@Valid @RequestBody UserRequest userRequest);

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  User get(@PathVariable UUID id);

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  void delete(@PathVariable UUID id);

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  User update(@Valid @RequestBody User user);

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<User> findAll(@RequestParam(required = false) List<UUID> ids);
}

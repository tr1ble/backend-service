package com.epam.learn.backendservice.api;

import com.epam.learn.backendservice.model.UserModel;
import com.epam.learn.backendservice.service.impl.dto.UserRequest;
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
  UserModel create(@Valid @RequestBody UserRequest userRequest);

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  UserModel get(@PathVariable UUID id);

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  void delete(@PathVariable UUID id);

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  UserModel update(@Valid @RequestBody UserModel user);

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<UserModel> findAll(@RequestParam(required = false) List<UUID> ids);

  @PostMapping("/send/{id}")
  @ResponseStatus(HttpStatus.OK)
  void send(@PathVariable UUID id);
}

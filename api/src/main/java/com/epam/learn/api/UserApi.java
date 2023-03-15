package com.epam.learn.api;

import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Users API", description = "Users API interface")
public interface UserApi {
  @Operation(summary = "Create user", description = "Create new user")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  UserResponseDto create(@Valid @RequestBody UserRequestDto userRequest);

  @Operation(summary = "Get user by ID", description = "Read user by ID")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  UserResponseDto get(@PathVariable UUID id);

  @Operation(summary = "Delete user", description = "Remove user by ID")
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  void delete(@PathVariable UUID id);

  @Operation(summary = "Update user", description = "Rewrite user")
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  UserResponseDto update(@Valid @RequestBody UserRequestDto user);

  @Operation(summary = "Get users", description = "Read all users")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<UserResponseDto> findAll(@RequestParam(required = false) List<UUID> ids);

  @Operation(summary = "Send user", description = "Send user to consumer")
  @PostMapping("/send/{id}")
  @ResponseStatus(HttpStatus.OK)
  void send(@PathVariable UUID id);
}

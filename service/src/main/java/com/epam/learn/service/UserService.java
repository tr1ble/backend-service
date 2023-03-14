package com.epam.learn.service;

import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
  @Transactional
  UserResponseDto create(UserRequestDto user);
  @Transactional
  List<UserResponseDto> getById(List<UUID> ids);
  @Transactional
  List<UserResponseDto> getAll();
  @Transactional
  UserResponseDto getById(UUID id);
  @Transactional
  void deleteById(UUID id);
  @Transactional
  UserResponseDto update(UserRequestDto user);
  @Transactional
  void send(UUID id);
  @Transactional
  User getDtoById(UUID id);
}

package com.epam.learn.service;

import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
  UserResponseDto create(UserRequestDto user);
  List<UserResponseDto> getById(List<UUID> ids);
  List<UserResponseDto> getAll();
  UserResponseDto getById(UUID id);
  void deleteById(UUID id);
  UserResponseDto update(UserRequestDto user);
  void send(UUID id);
  User getDtoById(UUID id);
}

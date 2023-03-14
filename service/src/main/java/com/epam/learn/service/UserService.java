package com.epam.learn.service;

import com.epam.learn.dto.user.UserDto;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
  @Transactional(isolation = Isolation.SERIALIZABLE)
  UserResponseDto create(UserRequestDto user);
  @Transactional(isolation = Isolation.READ_COMMITTED)
  List<UserResponseDto> getById(List<UUID> ids);
  @Transactional(isolation = Isolation.READ_COMMITTED)
  List<UserResponseDto> getAll();
  @Transactional(isolation = Isolation.READ_COMMITTED)
  UserResponseDto getById(UUID id);
  @Transactional(isolation = Isolation.SERIALIZABLE)
  void deleteById(UUID id);
  @Transactional(isolation = Isolation.SERIALIZABLE)
  UserResponseDto update(UserRequestDto user);
  @Transactional
  void send(UUID id);
  @Transactional(isolation = Isolation.READ_COMMITTED)
  UserDto getDtoById(UUID id);
}

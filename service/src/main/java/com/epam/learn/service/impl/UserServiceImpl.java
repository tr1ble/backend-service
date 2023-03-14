package com.epam.learn.service.impl;

import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.mapper.UserMapper;
import com.epam.learn.messaging.producer.UserProducer;
import com.epam.learn.repository.UserRepository;
import com.epam.learn.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UserProducer userProducer;

  @Override
  public UserResponseDto create(UserRequestDto userRequest) {
    User user = userRepository.save(userMapper.mapToDomain(userRequest));
    return userMapper.mapToResponse(user);
  }

  @Override
  public List<UserResponseDto> getById(List<UUID> ids) {
    Iterable<User> users = userRepository.findAllById(ids);
    return StreamSupport
        .stream(users.spliterator(), false)
        .map(userMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserResponseDto> getAll() {
    Iterable<User> users = userRepository.findAll();
    return StreamSupport
        .stream(users.spliterator(), false)
        .map(userMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public UserResponseDto getById(UUID id) {
    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return userMapper.mapToResponse(user);
  }

  @Override
  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserResponseDto update(UserRequestDto user) {
    User userDto = userMapper.mapToDomain(user);
    return userMapper.mapToResponse(userRepository.save(userDto));
  }

  @Override
  public void send(UUID id) {
    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    userProducer.produceUser(userMapper.mapToAvro(user));
  }

  @Override
  public User getDtoById(UUID id) {
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}

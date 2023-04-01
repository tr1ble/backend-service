package com.epam.learn.service.impl;

import com.epam.learn.model.Role;
import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.mapper.UserMapper;
import com.epam.learn.messaging.producer.UserProducer;
import com.epam.learn.repository.UserRepository;
import com.epam.learn.service.SecurityService;
import com.epam.learn.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UserProducer userProducer;
  private final PasswordEncoder passwordEncoder;
  private final SecurityService securityService;

  @Override
  public UserResponseDto create(UserRequestDto userRequest) {
    User mappedUser = userMapper.mapToDomain(userRequest);
    mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
    if(mappedUser.getRole() == null) {
      mappedUser.setRole(Role.USER);
    }
    User user = userRepository.save(mappedUser);
    return userMapper.mapToResponse(user);
  }

  @Override
  public List<UserResponseDto> getById(List<UUID> ids) {
    List<User> users = userRepository.findAllById(ids);
    return users.stream()
        .map(userMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserResponseDto> getAll() {
    List<User> users = userRepository.findAll();
    return users.stream()
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

  @Override
  public UserDetails loadUserByUsername(String username) {
    if(securityService.isBlocked(username)) {
      throw new LockedException("blocked");
    }
    User user = userRepository.findByUsername(username);
    if (user == null) throw new UsernameNotFoundException(username);

    return user;
  }
}

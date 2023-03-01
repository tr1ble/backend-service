package com.epam.learn.backendservice.service.impl;

import com.epam.learn.backendservice.dto.UserRequest;
import com.epam.learn.backendservice.mapper.UserMapper;
import com.epam.learn.backendservice.model.User;
import com.epam.learn.backendservice.repository.UserRepository;
import com.epam.learn.backendservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public User create(UserRequest userRequest) {
    return userRepository.save(userMapper.mapToDomain(userRequest));
  }

  public List<User> getById(List<UUID> ids) {
    Iterable<User> users = userRepository.findAllById(ids);
    return StreamSupport
        .stream(users.spliterator(), false)
        .collect(Collectors.toList());
  }

  public List<User> getAll() {
    Iterable<User> users = userRepository.findAll();
    return StreamSupport
        .stream(users.spliterator(), false)
        .collect(Collectors.toList());
  }

  public User getById(UUID id) {
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }

  public User update(User user) {
    return userRepository.save(user);
  }
}

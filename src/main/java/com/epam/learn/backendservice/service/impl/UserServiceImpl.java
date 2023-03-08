package com.epam.learn.backendservice.service.impl;

import com.epam.learn.backendservice.messaging.producer.UserProducer;
import com.epam.learn.backendservice.model.UserModel;
import com.epam.learn.User;
import com.epam.learn.backendservice.service.impl.dto.UserRequest;
import com.epam.learn.backendservice.mapper.UserMapper;
import com.epam.learn.backendservice.repository.UserRepository;
import com.epam.learn.backendservice.service.UserService;
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
  public UserModel create(UserRequest userRequest) {
    return userRepository.save(userMapper.mapToDomain(userRequest));
  }

  @Override
  public List<UserModel> getById(List<UUID> ids) {
    Iterable<UserModel> users = userRepository.findAllById(ids);
    return StreamSupport
        .stream(users.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserModel> getAll() {
    Iterable<UserModel> users = userRepository.findAll();
    return StreamSupport
        .stream(users.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public UserModel getById(UUID id) {
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserModel update(UserModel user) {
    return userRepository.save(user);
  }

  @Override
  public void send(UUID id) {
    UserModel user = getById(id);
    userProducer.produceUser(userMapper.mapToAvro(user));
  }
}

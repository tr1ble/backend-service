package com.epam.learn.backendservice.service;

import com.epam.learn.backendservice.dto.UserRequest;
import com.epam.learn.backendservice.mapper.UserMapper;
import com.epam.learn.backendservice.model.User;
import com.epam.learn.backendservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public interface UserService {
  User create(UserRequest userRequest);
  List<User> getById(List<UUID> ids);
  List<User> getAll();
  User getById(UUID id);
  void deleteById(UUID id);
  User update(User user);
}

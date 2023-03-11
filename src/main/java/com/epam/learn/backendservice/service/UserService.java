package com.epam.learn.backendservice.service;

import com.epam.learn.backendservice.model.UserModel;
import com.epam.learn.backendservice.service.impl.dto.UserRequest;
import java.util.List;
import java.util.UUID;

public interface UserService {
  UserModel create(UserRequest userRequest);
  List<UserModel> getById(List<UUID> ids);
  List<UserModel> getAll();
  UserModel getById(UUID id);
  void deleteById(UUID id);
  UserModel update(UserModel user);
  void send(UUID id);
}

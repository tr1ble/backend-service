package com.epam.learn.backendservice.mapper;

import com.epam.learn.User;
import com.epam.learn.backendservice.config.MappersConfig;
import com.epam.learn.backendservice.model.UserModel;
import com.epam.learn.backendservice.service.impl.dto.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  UserModel mapToDomain(UserRequest model);

  @Mapping(target = "id")
  User mapToAvro(UserModel user);
}

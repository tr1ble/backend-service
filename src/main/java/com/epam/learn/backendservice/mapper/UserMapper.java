package com.epam.learn.backendservice.mapper;

import com.epam.learn.backendservice.config.MappersConfig;
import com.epam.learn.backendservice.dto.UserRequest;
import com.epam.learn.backendservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  User mapToDomain(UserRequest model);
}

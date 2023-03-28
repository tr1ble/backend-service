package com.epam.learn.mapper;

import com.epam.learn.config.MappersConfig;
import com.epam.learn.dto.authentication.AuthenticationRequest;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import com.epam.learn.model.Role;
import com.epam.learn.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public abstract class AuthMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", ignore = true)
  public abstract User mapToUser(AuthenticationRequest source);
}

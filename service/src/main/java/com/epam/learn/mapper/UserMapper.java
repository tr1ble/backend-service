package com.epam.learn.mapper;

import com.epam.learn.config.MappersConfig;
import com.epam.learn.model.Role;
import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public abstract class UserMapper {

  protected static Role mapRoleToEnum(String role) {
    return Role.valueOf(role);
  }

  protected static String mapRoleToString(Role role) {
    return role.name();
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", expression = "java(UserMapper.mapRoleToEnum(source.getRole()))")
  public abstract User mapToDomain(UserRequestDto source);

  @Mapping(target = "role", expression = "java(UserMapper.mapRoleToString(source.getRole()))")
  public abstract com.epam.learn.User mapToAvro(User source);

  public abstract UserResponseDto mapToResponse(User source);
}

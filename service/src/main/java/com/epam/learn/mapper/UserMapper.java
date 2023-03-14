package com.epam.learn.mapper;

import com.epam.learn.config.MappersConfig;
import com.epam.learn.model.User;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  User mapToDomain(UserRequestDto source);
  com.epam.learn.User mapToAvro(User user);
  UserResponseDto mapToResponse(User source);
}

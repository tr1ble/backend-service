package com.epam.learn.mapper;

import com.epam.learn.User;
import com.epam.learn.config.MappersConfig;
import com.epam.learn.dto.user.UserDto;
import com.epam.learn.dto.user.UserRequestDto;
import com.epam.learn.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  UserDto mapToDomain(UserRequestDto source);
  User mapToAvro(UserDto user);
  UserResponseDto mapToResponse(UserDto source);
}
